package com.revature.controller;

import com.revature.dto.Message;
import com.revature.exception.OrderNotFoundException;
import com.revature.model.Order;
import com.revature.service.OrderService;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLOutput;
import java.util.List;

public class OrderController implements Controller {
    private OrderService orderService = new OrderService();

    @Override
    public void mapEndpoint(Javalin app) {
        // VIEW ORDERS
        app.get("/orders", ctx -> {
            List<Order> allDevelopers =  orderService.getAllOrders();
            ctx.json(allDevelopers);
        });

        // FILTER ORDERS
        app.get("/filter-order", ctx -> {
            Order order = new Order();

            if (order.getFilterID() == null) {
                ctx.json(new Message("Order Trying To Be Filtered Was Not Found!"));
                ctx.status(400);
            } else {
                try {
                    Order getService = orderService.filterOrder(order.getFilterID());
                    ctx.status(200);
                    ctx.json(getService);
                } catch (OrderNotFoundException e) {
                    ctx.req().getSession().invalidate();
                    ctx.status(400);
                    ctx.json(new Message(e.getMessage()));
                }
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
