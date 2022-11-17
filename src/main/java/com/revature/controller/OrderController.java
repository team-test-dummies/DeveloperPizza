package com.revature.controller;

import com.revature.data.exception.ForbiddenException;
import com.revature.data.exception.DataNotFoundException;
import com.revature.data.exception.UnauthorizedException;
import com.revature.data.records.Authority;
import com.revature.data.records.Order;
import com.revature.service.OrderService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.UnauthorizedResponse;

import java.sql.SQLException;
import java.util.List;

public class OrderController {

    public static void getOrders(Context context) throws SQLException {
        Authority authority = (Authority) context.req().getSession().getAttribute("authority");
        List<Order> orders = OrderService.getOrders(authority.id());
        context.json(orders);
    }

    // creates a series of (or single order)
    public static void postOrders(Context context) throws SQLException {
        Authority authority = (Authority) context.req().getSession().getAttribute("authority");
        Order pending = context.bodyAsClass(Order.class);
        OrderService.postOrder(authority.id(), pending);
    }

    public static void getOrder(Context context) {
        /* /orders/{order-id} */
        throw new Error("unimplemented");
    }

    public static void putOrder(Context context) throws SQLException, ForbiddenException {
        try {
            // make sure the user is authorized
            Authority authority = (Authority) context.req().getSession().getAttribute("authority");
            int orderID = Integer.parseInt(context.pathParam("order-id"));
            OrderService.authorize(authority, orderID);
            // replace the order with user provided order
            Order pending = context.bodyAsClass(Order.class);
            OrderService.putOrder(authority.id(), pending);
        }
        catch (DataNotFoundException e) {
            context.status(HttpStatus.BAD_REQUEST);
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    public static void deleteOrder(Context context) throws ForbiddenException, SQLException, DataNotFoundException {
        try {
            /* /orders/{order-id} */
            Authority authority = (Authority) context.req().getSession().getAttribute("authority");
            int orderID = Integer.parseInt(context.pathParam("order-id"));
            OrderService.authorize(authority, orderID);
            // remove the order
            OrderService.deleteOrder(orderID);
            context.status(HttpStatus.NO_CONTENT);
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
        catch (ForbiddenException e) {
            context.status(HttpStatus.FORBIDDEN);
        }
        catch (DataNotFoundException e) {
            context.status(HttpStatus.NO_CONTENT);
        }
    }
}
