package com.revature.dao;

import com.revature.model.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    // VIEW ORDERS
    public List<Order> getAllOrders() throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("select * from orders");
            ResultSet rs = pstmt.executeQuery();
            List<Order> allOrders = new ArrayList<>();

            while (rs.next()) {
                String filterID         = rs.getString("filterid");
                String skillset         = rs.getString("skillset");
                String location         = rs.getString("location");
                String availability     = rs.getString("availability");
                String salary           = rs.getString("salary");
                String experience       = rs.getString("experience");
                String education        = rs.getString("education");
                String certifications   = rs.getString("certifications");
                String languages        = rs.getString("languages");
                String frameworks       = rs.getString("frameworks");
                String databases        = rs.getString("databases");
                String operatingsystems = rs.getString("operatingsystems");
                String tools            = rs.getString("tools");
                String hobbies          = rs.getString("hobbies");

                Order order = new Order(filterID, skillset, location, availability, salary, experience, education, certifications, languages, frameworks, databases, operatingsystems, tools, hobbies);

                allOrders.add(order);
            }
            return allOrders;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // FILTER ORDER
    public Order filterOrder(String filterid) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from orders where filterid=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, filterid);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setFilterID(rs.getString('1')); // CHANGE TO FIND OTHER ORDERS

                return order;
            } else {
                return null;
            }
        }
    }
}
