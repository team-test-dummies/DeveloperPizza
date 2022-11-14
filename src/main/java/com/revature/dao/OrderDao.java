package com.revature.dao;

import com.revature.model.Order;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    // VIEW ORDERS
    public List<Order> getAllOrders() throws SQLException, IOException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from orders");
            ResultSet rs = ps.executeQuery();
            List<Order> allOrders = new ArrayList<>();

            while (rs.next()) {
                allOrders.add(
                        new Order(
                                rs.getInt("id"),
                                rs.getString("skillset"),
                                rs.getString("location"),
                                rs.getString("availability"),
                                rs.getString("salary"),
                                rs.getString("experience"),
                                rs.getString("education"),
                                rs.getString("certifications"),
                                rs.getString("languages"),
                                rs.getString("frameworks"),
                                rs.getString("databases"),
                                rs.getString("operatingsystems"),
                                rs.getString("tools"),
                                rs.getInt("orderID")
                        )
                );
            }
            return allOrders;
        }
    }

    // FILTER ORDER
    public List<Order> filterOrderID(int filterID) throws SQLException, IOException {
        Order order = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from orders where orderID = ?");
            ps.setInt(1, filterID);
            ResultSet rs = ps.executeQuery();
            List<Order> filteredOrders = new ArrayList<>();

            while (rs.next()) {
                Order filterOrder = new Order(
                        rs.getInt("id"),
                        rs.getString("skillset"),
                        rs.getString("location"),
                        rs.getString("availability"),
                        rs.getString("salary"),
                        rs.getString("experience"),
                        rs.getString("education"),
                        rs.getString("certifications"),
                        rs.getString("languages"),
                        rs.getString("frameworks"),
                        rs.getString("databases"),
                        rs.getString("operatingsystems"),
                        rs.getString("tools"),
                        rs.getInt("orderID")
                );
                filteredOrders.add(filterOrder);
            }
            return filteredOrders;
        }
    }

    // CREATE ORDER
    public int addOrder(Order orderAdd) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            String sql = "insert into orders () values ()";
            PreparedStatement ps = connection.prepareStatement(sql);
        }
    }

    // EDIT ORDER

    // DELETE ORDER
}
