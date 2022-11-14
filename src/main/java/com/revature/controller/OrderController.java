package com.revature.controller;

import com.revature.dto.Message;
import com.revature.exception.OrderNotFoundException;
import com.revature.model.Order;
import com.revature.service.OrderService;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLOutput;
import java.util.List;

public class OrderController {

    @Deprecated
    public void mapEndpoint(Javalin app) {
        // VIEW ORDERS
        app.get("/orders", ctx -> {
            List<Order> allOrders = OrderService.getAllOrders();
            ctx.json(allOrders);
        });

        // FILTER ORDERS
        app.get("/filter-order/{filter_id}", ctx -> {
            String getFilterID = ctx.pathParam("filter_id");

            try {
                int filterID = Integer.parseInt(getFilterID);
                List<Order> filteredOrders = OrderService.getOrderByOrderID(filterID);
                ctx.json(filteredOrders);
                ctx.status(200);
            } catch (OrderNotFoundException e) {
                ctx.result("orderID " + getFilterID + " was invalid!");
                ctx.status(400);
            }
        });

        // CREATE ORDER --> get
        app.post("/create-order", ctx -> {
           ctx.json(new Message("Connected"));
        });

        // EDIT ORDER --> post
        app.put("/edit-order", ctx -> {
            ctx.json(new Message("Connected"));
        });

        // DELETE ORDER --> delete
        app.delete("/delete-order", ctx -> {
            ctx.json(new Message("Connected"));
        });
    }
}
