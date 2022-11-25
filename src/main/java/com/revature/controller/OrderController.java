package com.revature.controller;

import com.revature.dao.Dao;
import com.revature.data.enums.Role;
import com.revature.data.exception.ForbiddenException;
import com.revature.data.exception.FourOhFourException;
import com.revature.data.exception.UnauthorizedException;
import com.revature.data.exception.ValidationException;
import com.revature.data.records.Authority;
import com.revature.data.records.Order;
import com.revature.service.Authorities;
import com.revature.service.OrderService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.openapi.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderController {

    @OpenApi(
            summary = "Get all orders available to user",
            operationId = "getOrders",
            path = "/orders",
            methods = HttpMethod.GET,
            tags = {"Order"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Order[].class)}),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void getOrders(Context context) throws SQLException {
        List<Order> orders;
        try {
            Authority authority = Authorities.getAuthority(context);
            try (Connection connection = Dao.createConnection()) {
                // authority determines the scope of the get
                if (
                    authority.role().equals(Role.STAFF) ||
                    authority.role().equals(Role.ADMIN)
                ) orders = OrderService.selectAll(connection);
                else orders = OrderService.selectAll(connection, authority.id());
            }
            // close connection before responding
            context.status(HttpStatus.OK);
            context.json(orders);
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    // creates a series of (or single order)

    @OpenApi(
            summary = "Post one or more orders",
            operationId = "postOrders",
            path = "/orders",
            methods = HttpMethod.POST,
            tags = {"Order"},
            requestBody = @OpenApiRequestBody(content = @OpenApiContent(from = Order[].class)),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "403"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void postOrders(Context context) throws SQLException {
        try {
            Authority authority = Authorities.getAuthority(context);
            List<Order> pendings = OrderService.validateAll(context, authority);
            // validation should not check for authority in this case, but anyone who is validated should have authority
            try (Connection connection = Dao.createConnection()) {
                OrderService.authorize(connection, authority, pendings.parallelStream().map(order -> order.userId()));
                connection.setAutoCommit(false);
                for (Order pending : pendings) {
                    Order.insert(pending, connection);
                }
                connection.commit();
            }
            context.status(HttpStatus.CREATED);
            context.header("Location", "/orders"); // should use context location
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
        catch (ForbiddenException e) {
            context.status(HttpStatus.FORBIDDEN);
        }
        catch (ValidationException e) {
            postOrder(context);
        }
    }

    // used on the orders endpoint if a single order is posted
    @OpenApi(
            summary = "Post new order",
            operationId = "postOrder",
            path = "/orders/",
            methods = HttpMethod.POST,
            tags = {"Order"},
            requestBody = @OpenApiRequestBody(content = @OpenApiContent(from = Order.class)),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "403"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void postOrder(Context context) throws SQLException {
        Integer generatedOrderId;
        try {
            Authority authority = Authorities.getAuthority(context);
            Order pending = OrderService.validate(context, authority);
            try (Connection connection = Dao.createConnection()) {
                // merely having authority allows you to post
                connection.setAutoCommit(false);
                generatedOrderId = Order.insert(pending, connection);
                connection.commit();
            }
            context.status(HttpStatus.CREATED);
            StringBuilder builder = new StringBuilder("/orders");
            builder.append("/" + generatedOrderId);
            context.header("Location", builder.toString());
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
        catch (ValidationException e) {
            context.status(HttpStatus.BAD_REQUEST);
        }
    }

    @OpenApi(
            summary = "Get specific order",
            operationId = "getOrder",
            path = "/orders/{order-id}",
            methods = HttpMethod.GET,
            tags = {"Order"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Order.class)}),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "404"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void getOrder(Context context) throws SQLException {
        Order result;
        try {
            Authority authority = Authorities.getAuthority(context);
            int orderId = Integer.parseInt(context.pathParam("order-id"));
            try (Connection connection = Dao.createConnection()){
                OrderService.authorize(connection, authority, orderId);
                result = OrderService.get(connection, orderId);
            }
            context.status(HttpStatus.OK);
            context.json(result);
        }
        catch (FourOhFourException e) {
            context.status(HttpStatus.NOT_FOUND);
        }
        catch (ForbiddenException e) {
            context.status(HttpStatus.FORBIDDEN);
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    @OpenApi(
            summary = "Replace an existing order with request body",
            operationId = "putOrder",
            path = "/orders/{order-id}",
            methods = HttpMethod.PUT,
            tags = {"Order"},
            requestBody = @OpenApiRequestBody(content = @OpenApiContent(from = Order.class)),
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "403"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void putOrder(Context context) throws SQLException {
        try {
            Authority authority = Authorities.getAuthority(context);
            int orderId = Integer.parseInt(context.pathParam("order-id"));
            Order pending = OrderService.validate(context, authority, orderId);
            try (Connection connection = Dao.createConnection()){
                OrderService.authorize(connection, authority, orderId);
                connection.setAutoCommit(false);
                Order.update(pending, connection);
                connection.commit();
            }
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
        // making exceptions based on the http statuses is a bad idea, here we have to treat of 404Exception like a Bad Request whtich is confusing
        catch (FourOhFourException e) { // the database cannot currently create orders out of order
            context.status(HttpStatus.BAD_REQUEST);
        }
    }

    @OpenApi(
            summary = "Delete an existing order from the database",
            operationId = "deleteOrder",
            path = "/orders/{order-id}",
            methods = HttpMethod.DELETE,
            tags = {"Order"},
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "403"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void deleteOrder(Context context) throws SQLException {
        try {
            Authority authority = Authorities.getAuthority(context);
            int orderId = Integer.parseInt(context.pathParam("order-id"));
            try (Connection connection = Dao.createConnection()) {
                OrderService.authorize(connection, authority, orderId);
                connection.setAutoCommit(false);
                OrderService.delete(connection, orderId);
                connection.commit();
            }
            // close connection before returning result
            context.status(HttpStatus.NO_CONTENT);
        }
        catch (ForbiddenException e) {
            context.status(HttpStatus.FORBIDDEN);
        }
        catch (UnauthorizedException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
        // making exceptions based on the http statuses is a bad idea, here we have to treat of 404Exception like a success withc is confusing
        catch (FourOhFourException e) { // the object never existed so the call succeeds
            context.status(HttpStatus.NO_CONTENT);
        }
    }
}
