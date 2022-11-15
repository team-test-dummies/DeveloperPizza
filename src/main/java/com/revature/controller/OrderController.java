package com.revature.controller;

import com.revature.dto.Message;
import com.revature.dto.RegisterInfo;
import com.revature.exception.OrderNotFoundException;
import com.revature.exception.OrderUnsuccessfullyAddedException;
import com.revature.exception.RegisterException;
import com.revature.model.Order;
import com.revature.service.EmployerService;
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
        app.get("/order/filter/{filter_id}", ctx -> {
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
        app.post("/order/create", ctx -> {
            Order addOrder = ctx.bodyAsClass(Order.class);

            if (addOrder.getSkillset().length() == 0) {
                ctx.result("Please select needed skill sets!");
                ctx.status(400);
            }
            else if (addOrder.getLocation().length() == 0) {
                ctx.result("Please select a proper location!");
                ctx.status(400);
            }
            else if (addOrder.getAvailability().length() == 0) {
                ctx.result("Please select availability option, Remote or On-Site!");
                ctx.status(400);
            }
            else if (addOrder.getSalary().length() == 0) {
                ctx.result("Please enter position salary");
                ctx.status(400);
            }
            else if (addOrder.getExperience().length() == 0) {
                ctx.result("Please enter years of experience, if needed");
                ctx.status(400);
            }
            else if (addOrder.getEducation().length() == 0) {
                ctx.result("Please enter eduction level required");
                ctx.status(400);
            }
            else if (addOrder.getCertifications().length() == 0) {
                ctx.result("Please enter needed certifications");
                ctx.status(400);
            } else if (addOrder.getLanguages().length() == 0) {
                    ctx.result("Please enter needed programming languages");
                    ctx.status(400);
            } else if (addOrder.getFrameworks().length() == 0) {
                ctx.result("Please enter needed frameworks");
                ctx.status(400);
            } else if (addOrder.getDatabases().length() == 0) {
                ctx.result("Please enter needed databases");
                ctx.status(400);
            } else if (addOrder.getOperatingsystems().length() == 0) {
                ctx.result("Please enter needed operating systems");
                ctx.status(400);
            } else if (addOrder.getTools().length() == 0) {
                ctx.result("Please enter needed tools");
                ctx.status(400);
            } else if (addOrder.getOrderID() == 0) {
                ctx.result("Enter the id corresponding to your employer");
                ctx.status(400);
            } else {
                try {
                    OrderService.createOrder(addOrder);
                    ctx.json(addOrder);
                    ctx.status(200);

                } catch (OrderUnsuccessfullyAddedException e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            }
        });

        // EDIT ORDER --> post
        app.put("/order/edit", ctx -> {
            Order editOrder = ctx.bodyAsClass(Order.class);

            try {
                OrderService.editOrder(editOrder);
                ctx.json(editOrder);
                ctx.status(200);
            } catch (OrderNotFoundException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            }
        });

        // DELETE ORDER --> delete
        app.delete("/order/delete", ctx -> {
/*            Order deleteOrder = ctx.bodyAsClass(Order.class);

            try {
                OrderService.deleteOrder(deleteOrder);
                ctx.json(deleteOrder);
                ctx.status(200);
            } catch (OrderNotFoundException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            }*/
        });
    }
}
