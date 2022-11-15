package com.revature.dao;

import com.revature.dto.RegisterInfo;
import com.revature.model.Employer;
import com.revature.model.Order;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends Dao {
    // VIEW ORDERS
    public static List<Order> getAllOrders() throws SQLException, IOException {
        try(Connection connection = createConnection()) {
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
    public static List<Order> filterOrderID(int filterID) throws SQLException, IOException {
        Order order = null;
        try (Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from orders right join users on orders.orderID=users.id where orderID = ?");
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
    public static int createOrder(Order addOrder) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into orders (skillset, location, availability, salary, experience, education, certifications, languages, frameworks, databases, operatingsystems, tools, orderID) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, addOrder.getSkillset());
            ps.setString(2, addOrder.getLocation());
            ps.setString(3, addOrder.getAvailability());
            ps.setString(4, addOrder.getSalary());
            ps.setString(5, addOrder.getExperience());
            ps.setString(6, addOrder.getEducation());
            ps.setString(7, addOrder.getCertifications());
            ps.setString(8, addOrder.getLanguages());
            ps.setString(9, addOrder.getFrameworks());
            ps.setString(10, addOrder.getDatabases());
            ps.setString(11, addOrder.getOperatingsystems());
            ps.setString(12, addOrder.getTools());
            ps.setInt(13, addOrder.getOrderID());

            int numberOfOrdersAdded = ps.executeUpdate();
            return numberOfOrdersAdded;
        }
    }

    // EDIT ORDER
    public static int editOrder(Order editOrder) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "update orders set skillset = ?, location = ?, availability = ?, salary = ?, experience = ?, education = ?, certifications = ?, languages = ?, frameworks = ?, databases = ?, operatingsystems = ?, tools = ?, orderID = ?" +
                            "where id = ?"
            );

            ps.setInt(14, editOrder.getId());
            ps.setString(1, editOrder.getSkillset());
            ps.setString(2, editOrder.getLocation());
            ps.setString(3, editOrder.getAvailability());
            ps.setString(4, editOrder.getSalary());
            ps.setString(5, editOrder.getExperience());
            ps.setString(6, editOrder.getEducation());
            ps.setString(7, editOrder.getCertifications());
            ps.setString(8, editOrder.getLanguages());
            ps.setString(9, editOrder.getFrameworks());
            ps.setString(10, editOrder.getDatabases());
            ps.setString(11, editOrder.getOperatingsystems());
            ps.setString(12, editOrder.getTools());
            ps.setInt(13, editOrder.getOrderID());

            int numberOfOrdersEdited= ps.executeUpdate();
            return numberOfOrdersEdited;
        }
    }

    // DELETE ORDER
    public static int deleteOrder(int deleteOrder) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from orders where id = ?");
            ps.setInt(1, deleteOrder);
            return ps.executeUpdate();
        }
    }
}
