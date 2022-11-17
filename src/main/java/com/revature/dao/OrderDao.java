package com.revature.dao;

import com.revature.data.records.Order;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}
