package com.revature.controller;

import com.revature.data.records.Authority;
import com.revature.data.records.Order;
import com.revature.service.OrderService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.io.IOException;
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

    public static void putOrder(Context context) {
        /* /orders/{order-id} */
        throw new Error("unimplemented");
    }

    public static void deleteOrder(Context context) {
        /* /orders/{order-id} */
        throw new Error("unimplemented");
    }
}
