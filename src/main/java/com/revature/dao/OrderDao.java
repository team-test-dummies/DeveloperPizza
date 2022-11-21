package com.revature.dao;

import com.revature.data.records.Order;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class OrderDao extends Dao {

    public static List<Order> getAllOrders() {
        return null;
    }
    public static List<Order> getOrders(int user_id) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM orders WHERE user_id = ?"
            );
            statement.setInt(1, user_id);
            ResultSet results = statement.executeQuery();
            return Order.fromAll(results);
        }
    }

    public static Order getOrder(int order_id) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM orders WHERE id = ?"
            );
            statement.setInt(1, order_id);
            ResultSet result = statement.executeQuery();
            result.next();
            return Order.from(result);
        }
    }

    public static int postOrder(Order pending) throws SQLException {
        int id;
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
            id = pending.insert(connection);
            connection.commit();
        }
        return id;
    }

    public static void putOrder(Order pending) throws SQLException {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
            pending.update(connection);
            connection.commit();
        }
    }

    public static void deleteOrder(int orderID) throws SQLException {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
            deleteOrder(
                getOrder(orderID)
            );
            connection.commit();
        }
    }
    public static void deleteOrder(Order pending) throws SQLException {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
            pending.delete(connection);
            connection.commit();
        }
    }
}
