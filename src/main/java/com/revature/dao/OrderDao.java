package com.revature.dao;

import com.revature.data.records.Order;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    private static void insertLanguages(Connection connection, int orderID, List<String> languages) throws SQLException {
        PreparedStatement selectID = connection.prepareStatement(
            "SELECT id FROM languages WHERE language = ?;"
        );
        PreparedStatement attachLanguage = connection.prepareStatement(
                "INSERT INTO orders_languages (order_id, language_id) VALUES (?, ?);"
        );
        for (String language : languages) {
            selectID.setString(1, language);
            ResultSet result = selectID.executeQuery();
            result.next();
            int languageID = result.getInt("id");
            attachLanguage.setInt(1, orderID);
            attachLanguage.setInt(2, languageID);
            attachLanguage.executeUpdate();
        }
    }

    private static void insertTools(Connection connection, int orderID, List<String> tools) throws SQLException {
        PreparedStatement selectID = connection.prepareStatement(
                "SELECT id FROM tools WHERE tool = ?;"
        );
        PreparedStatement attachTools = connection.prepareStatement(
                "INSERT INTO orders_tools (order_id, tool_id) VALUES (?, ?)"
        );
        for (String tool : tools) {
            selectID.setString(1, tool);
            ResultSet result = selectID.executeQuery();
            result.next();
            int toolID = result.getInt("id");
            attachTools.setInt(1, orderID);
            attachTools.setInt(2, toolID);
            attachTools.executeUpdate();
        }
    }

    public static void postOrder(int user_id, Order pending) throws SQLException {
        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);
            // 'closed' table field defaults to false
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders (user_id) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setInt(1, user_id);
            statement.executeUpdate();
            ResultSet generateds = statement.getGeneratedKeys();
            generateds.next();
            int orderID = generateds.getInt("id");
            insertLanguages(connection, orderID, pending.languages());
            insertTools(connection, orderID, pending.tools());
            connection.commit();
        }
    }
}
