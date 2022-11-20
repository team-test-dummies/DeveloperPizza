package com.revature.controller;

import com.revature.dao.OrderDao;
import com.revature.data.exception.ForbiddenException;
import com.revature.data.exception.UnauthorizedException;
import com.revature.data.exception.ValidationException;
import com.revature.data.records.Authority;
import com.revature.data.records.Order;
import com.revature.service.OrderService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;
import java.util.List;

public class OrderController {


    public static void getOrders(Context context) throws SQLException {
        Authority authority = (Authority) context.req().getSession().getAttribute("authority");
        List<Order> orders = OrderDao.getOrders(authority.id());
        context.json(orders);
    }

    // creates a series of (or single order)
    public static void postOrders(Context context) throws SQLException, ForbiddenException, UnauthorizedException {
        Authority authority;
        try {
            authority = (Authority) context.req().getSession().getAttribute("authority");
        }
        catch (Exception e) {
            throw new UnauthorizedException();
        }
        Order pending = context.bodyAsClass(Order.class);
        // authorize
        if (authority.id() != pending.userId()) throw new ForbiddenException();
        int orderId = OrderDao.postOrder(pending);
        context.status(HttpStatus.CREATED);
        StringBuilder builder = new StringBuilder("/orders");
        builder.append("/" + orderId);
        context.header("Location", builder.toString());
    }

    public static void getOrder(Context context) throws SQLException {
        Authority authority = (Authority) context.req().getSession().getAttribute("authority");
        int orderId = Integer.parseInt(context.pathParam("order-id"));
        Order order = OrderDao.getOrder(orderId);
        if (order.userId() == authority.id()) {
            context.json(order);
        }
        else {
            context.status(HttpStatus.FORBIDDEN);
        }
    }

    public static void putOrder(Context context) throws SQLException {
        try {
            // gather
            Order pending = context.bodyAsClass(Order.class);
            int orderId = Integer.parseInt(context.pathParam("order-id"));
            Authority authority = (Authority) context.req().getSession().getAttribute("authority");
            // validate /* should really be sanitize */
            if (
                    orderId != pending.id() ||
                    authority.id() != pending.userId()
            ) throw new ValidationException();
            // authorize
            OrderService.authorize(authority.id(), orderId);
            // replace
            OrderDao.putOrder(pending);
            // respond
            context.status(HttpStatus.NO_CONTENT);
        }
        catch (ValidationException e) {
            context.status(HttpStatus.BAD_REQUEST);
        }
        catch (ForbiddenException e) {
            context.status(HttpStatus.FORBIDDEN);
        }
    }

    public static void deleteOrder(Context context) throws SQLException {
        try {
            Authority authority = (Authority) context.req().getSession().getAttribute("authority");
            int orderID = Integer.parseInt(context.pathParam("order-id"));
            OrderService.authorize(authority.id(), orderID);
            // remove the order
            OrderDao.deleteOrder(orderID);
            context.status(HttpStatus.NO_CONTENT);
        }
        catch (ForbiddenException e) {
            context.status(HttpStatus.FORBIDDEN);
        }
    }
}
