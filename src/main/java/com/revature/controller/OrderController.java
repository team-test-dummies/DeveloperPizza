package com.revature.controller;

import com.revature.data.records.Order;
import com.revature.service.OrderService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderController {

//         Should be included on getOrders as a queryParam
//        // FILTER ORDERS
//        app.get("/filter-order/{filter_id}", ctx -> {
//            String getFilterID = ctx.pathParam("filter_id");
//
//            try {
//                int filterID = Integer.parseInt(getFilterID);
//                List<Order> filteredOrders = OrderService.getOrderByOrderID(filterID);
//                ctx.json(filteredOrders);
//                ctx.status(200);
//            } catch (OrderNotFoundException e) {
//                ctx.result("orderID " + getFilterID + " was invalid!");
//                ctx.status(400);
//            }
//        });

    public static void getOrders(Context context) {
        try {
            List<Order> allOrders = OrderService.getAllOrders();
            context.json(allOrders);
        }
        catch (SQLException | IOException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // creates a series of (or single order)
    public static void postOrders(Context context) {
        /* /orders */
        throw new Error("unimplemented");
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
