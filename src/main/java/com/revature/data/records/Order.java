package com.revature.data.records;


import com.revature.data.enums.Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public record Order(int id, String name, Education educationRequirement, int salary, boolean closed, Set<String> languages, Set<String> tools, int userId) {

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
        int userId = result.getInt("user_id");
        return new Order(orderID, name, educationRequirement, salary, closed, languages, tools, userId);
    }

    public static List<Order> fromAll(ResultSet results) throws SQLException {
        List<Order> templates = new ArrayList<>();
        while(results.next()) {
            templates.add(Order.from(results));
        }
        return Collections.unmodifiableList(templates);
    }

    private static void insertLanguages(Order order, Connection connection, int id, boolean exclude) throws SQLException {
        String questions = order.languages.stream().map(value -> "?").collect(Collectors.joining(", "));
        String flipper = exclude ? "NOT" : "";
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders_languages (order_id, language_id)\n" +
                        "\tSELECT ?, id FROM languages WHERE languages.language " + flipper + " IN (" + questions + ");"
        );
        statement.setInt(1, id);
        int i = 2;
        for (String language : order.languages) {
            statement.setString(i, language);
            i++;
        }
        statement.executeUpdate();
    }

    private static void deleteLanguages(Order order, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders_languages WHERE order_id = ?;"
        );
        statement.setInt(1, order.id);
        statement.executeUpdate();
    }

    private static void insertTools(Order order, Connection connection, int id, boolean exclude) throws SQLException {
        String questions = order.tools.stream().map(value -> "?").collect(Collectors.joining(", "));
        String flipper = exclude ? "NOT" : "";
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders_tools (order_id, tool_id)\n" +
                        "\tSELECT ?, id FROM tools WHERE tools.tool " + flipper + " IN (" + questions + ");"
        );
        statement.setInt(1, id);
        int i = 2;
        for (String tool : order.tools) {
            statement.setString(i, tool);
            i++;
        }
        statement.executeUpdate();
    }

    private static void deleteTools(Order order, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders_tools WHERE order_id = ?;"
        );
        statement.setInt(1, order.id);
        statement.executeUpdate();
    }


    private static int insertBody(Order order, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO orders (name, salary, education_requirement, closed, user_id) VALUES (?, ?, CAST (? AS education), ?, ?);",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        statement.setString(1, order.name);
        statement.setInt(2, order.salary);
        statement.setString(3, order.educationRequirement.name());
        statement.setBoolean(4, order.closed);
        statement.setInt(5, order.userId);
        statement.execute();
        ResultSet generateds = statement.getGeneratedKeys();
        generateds.next();
        return generateds.getInt("id");
    }

    private static void deleteBody(Order order, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders WHERE id = ?;"
        );
        statement.setInt(1, order.id);
        statement.executeUpdate();
    }

    public static void updateBody(Order order, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE orders SET name = ?, salary = ?, education_requirement = CAST (? AS education), closed = ?, user_id = ? WHERE id = ?;"
        );
        statement.setString(1, order.name);
        statement.setInt(2, order.salary);
        statement.setString(3, order.educationRequirement.name());
        statement.setBoolean(4, order.closed);
        statement.setInt(5, order.userId);
        statement.setInt(6, order.id);
        statement.executeUpdate();
    }

    public static int insert(Order order, Connection connection) throws SQLException {
        // the id will be dynamically generated
        // add the body because the associated tables will reference it
        // retrieve the generated id
        int id = insertBody(order, connection);
        // insert into associated tables
        insertLanguages(order, connection, id, false);
        insertTools(order, connection, id, false);
        return id;
    }

    public static void delete(Order order, Connection connection) throws SQLException {
        // the id is given in the record
        // delete any associated values connected to id
        deleteLanguages(order, connection);
        deleteTools(order, connection);
        // delete row connected to id
        deleteBody(order, connection);
    }

    public static void update(Order order, Connection connection) throws SQLException {
        // the id is given in the record
        // update the main row of the record with all values
        updateBody(order, connection);
        // delete any associated values not in the id
        deleteLanguages(order, connection);
        deleteTools(order, connection);
        // insert associated values (the tables will reject duplicates)
        insertLanguages(order, connection, order.id,false);
        insertTools(order, connection, order.id, false);
    }
}
