package com.revature.controller;

import com.revature.dao.OrderDao;
import com.revature.data.exception.ForbiddenException;
import com.revature.data.exception.UnauthorizedException;
import com.revature.data.exception.ValidationException;
import com.revature.data.records.Authority;
import com.revature.data.records.Order;
import com.revature.service.OrderService;
import io.cucumber.java.lv.Un;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.UnauthorizedResponse;

import java.sql.SQLException;
import java.util.List;

public class OrderController {


    public static void getOrders(Context context) throws SQLException {
        try {
            Authority authority = OrderService.authenticate(context);
            List<Order> orders = OrderDao.getOrders(authority.id());
            context.json(orders);
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    // creates a series of (or single order)
    public static void postOrders(Context context) throws SQLException, ForbiddenException {
        try {
            Authority authority = OrderService.authenticate(context);
            Order pending = context.bodyAsClass(Order.class);
            // authorize
            if (authority.id() != pending.userId()) throw new ForbiddenException();
            int orderId = OrderDao.postOrder(pending);
            context.status(HttpStatus.CREATED);
            StringBuilder builder = new StringBuilder("/orders");
            builder.append("/" + orderId);
            context.header("Location", builder.toString());
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    public static void getOrder(Context context) throws SQLException {
        try {
            Authority authority = OrderService.authenticate(context);
            int orderId = Integer.parseInt(context.pathParam("order-id"));
            Order order = OrderDao.getOrder(orderId);
            if (order.userId() == authority.id()) {
                context.json(order);
            } else {
                context.status(HttpStatus.FORBIDDEN);
            }
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    public static void putOrder(Context context) throws SQLException {
        try {
            Authority authority = OrderService.authenticate(context);
            // gather
            Order pending = context.bodyAsClass(Order.class);
            int orderId = Integer.parseInt(context.pathParam("order-id"));
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
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    public static void deleteOrder(Context context) throws SQLException {
        try {
            Authority authority = OrderService.authenticate(context);
            int orderID = Integer.parseInt(context.pathParam("order-id"));
            OrderService.authorize(authority.id(), orderID);
            // remove the order
            OrderDao.deleteOrder(orderID);
            context.status(HttpStatus.NO_CONTENT);
        }
        catch (ForbiddenException e) {
            context.status(HttpStatus.FORBIDDEN);
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }
}
