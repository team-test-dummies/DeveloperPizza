package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.data.enums.Role;
import com.revature.data.exception.ForbiddenException;
import com.revature.data.exception.FourOhFourException;
import com.revature.data.exception.ValidationException;
import com.revature.data.records.Authority;
import com.revature.data.records.Order;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OrderService {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void authorize(Connection connection, Authority authority, int orderId) throws ForbiddenException, SQLException, FourOhFourException {
        Integer foundUserId = userId(connection, orderId);
        if (authority.role().equals(Role.STAFF) || authority.role().equals(Role.ADMIN)) return;
        else if (authority.id() == foundUserId) return;
        else if (null == foundUserId) throw new FourOhFourException();
        else throw new ForbiddenException();
    }

    public static void authorize(Connection connection, Authority authority, Stream<Integer> orderIds) throws ForbiddenException, SQLException {
        if (authority.role().equals(Role.STAFF) || authority.role().equals(Role.ADMIN)) return;
        else if (orderIds.allMatch(orderId -> {
            return authority.id() == orderId;
        })) return;
        else throw new ForbiddenException();
    }

    public static Integer userId(Connection connection, int orderId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT user_id FROM orders WHERE id = ?");
        statement.setInt(1, orderId);
        ResultSet result = statement.executeQuery();
        if (result.next()) return result.getInt("user_id");
        else return null;
    }

    public static Order get(Connection connection, int orderId) throws SQLException, FourOhFourException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?;");
        statement.setInt(1, orderId);
        ResultSet result = statement.executeQuery();
        if (result.next()) return Order.from(result);
        else throw new FourOhFourException();
    }

    public static void delete(Connection connection, int orderId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders_languages WHERE order_id = ?;"
        );
        statement.setInt(1, orderId);
        statement.executeUpdate();
        statement = connection.prepareStatement(
                "DELETE FROM orders_tools WHERE order_id = ?;"
        );
        statement.setInt(1, orderId);
        statement.executeUpdate();
        statement = connection.prepareStatement(
                "DELETE FROM orders WHERE id = ?;"
        );
        statement.setInt(1, orderId);
        statement.executeUpdate();
    }

    public static Order validate(Context context, Authority authority, int orderId) throws ValidationException {
        try {
            return context.bodyAsClass(Order.class);
        }
        catch (Exception e) { // should be a more specific exception
            throw new ValidationException();
        }
    }


    public static List<Order> validateAll(Context context, Authority authority) throws ValidationException {
        try {
            Order[] orders = context.bodyAsClass(Order[].class); // pretty sure this is where we get unchecked or unsafe type operation
            return Arrays.stream(orders).toList();
        }
        catch (Exception e) { // should be a more specific exception
            throw new ValidationException();
        }
    }

    public static Order validate(Context context, Authority authority) throws ValidationException {
        Order pending;
        try {
            pending = context.bodyAsClass(Order.class);
        }
        catch (Exception e) { // should be a more specific exception
            throw new ValidationException();
        }
        if (
                authority.role().equals(Role.STAFF) ||
                authority.role().equals(Role.ADMIN)
        ) return pending;
        else if (
                authority.id() == pending.userId()
        ) return pending;
        else throw new ValidationException();
    }

    public static List<Order> selectAll(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM orders;"
        );
        return Order.fromAll(statement.executeQuery());
    }

    public static List<Order> selectAll(Connection connection, int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM orders WHERE user_id = ?;"
        );
        statement.setInt(1, userId);
        return Order.fromAll(statement.executeQuery());
    }

}
