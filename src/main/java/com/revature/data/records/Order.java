package com.revature.data.records;


import com.revature.data.enums.Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

// education should have its own enum
public record Order(int id, String name, Education educationRequirement, int salary, boolean closed, Set<String> languages, Set<String> tools) {
    private static Set<String> getLanguages(Connection connection, int orderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT languages.language FROM orders_languages\n" +
                        "LEFT OUTER JOIN languages ON orders_languages.language_id = languages.id\n" +
                        "WHERE orders_languages.order_id = ?;"
        );
        statement.setInt(1, orderID);
        ResultSet results = statement.executeQuery();
        Set<String> languages = new HashSet<>();
        while(results.next()) {
            languages.add(results.getString("language"));
        }
        return Collections.unmodifiableSet(languages);
    }

    private static Set<String> getTools(Connection connection, int orderID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT tools.tool FROM orders_tools\n" +
                        "LEFT OUTER JOIN tools ON orders_tools.tool_id = tools.id\n" +
                        "WHERE orders_tools.order_id = ?;"
        );
        statement.setInt(1, orderID);
        ResultSet results = statement.executeQuery();
        Set<String> tools = new HashSet<>();
        while(results.next()) {
            tools.add(results.getString("tool"));
        }
        return Collections.unmodifiableSet(tools);
    }

    // using static from so it does not interact with fasterXMLJSON
    public static Order from(ResultSet result) throws SQLException {
        int orderID = result.getInt("id");
        String name = result.getString("name");
        Education educationRequirement = Education.valueOf(
                result.getString("education_requirement")
        );
        int salary = result.getInt("salary");
        boolean closed = result.getBoolean("closed");
        Connection connection = result.getStatement().getConnection();
        Set<String> languages = getLanguages(connection, orderID);
        Set<String> tools = getTools(connection, orderID);
        return new Order(orderID, name, educationRequirement, salary, closed, languages, tools);
    }

    public static List<Order> fromAll(ResultSet results) throws SQLException {
        List<Order> templates = new ArrayList<>();
        while(results.next()) {
            templates.add(Order.from(results));
        }
        return Collections.unmodifiableList(templates);
    }
}
