package com.revature.data.records;


import com.revature.data.enums.Education;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    private void insertLanguages(Connection connection, int id, boolean exclude) throws SQLException {
        String flipper = exclude ? "NOT" : "";
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders_languages (order_id, language_id)\n" +
                        "\tSELECT ?, id FROM languages WHERE languages.language " + flipper + " IN ?\n" +
                        "ON CONFLICT DO NOTHING;"
        );
        statement.setInt(1, id);
        statement.setArray(2, connection.createArrayOf("VARCHAR(200)", languages.toArray()));
        statement.executeUpdate();
    }

    private void deleteLanguages(Connection connection, boolean exclude) throws SQLException {
        String flipper = exclude ? "NOT" : "";
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders_languages WHERE order_id = ? AND language_id IN (\n" +
                        "\tSELECT id FROM languages WHERE languages.language " + flipper + " IN ?\n" +
                        ");"
        );
        statement.setInt(1, id);
        statement.setArray(2, connection.createArrayOf("VARCHAR(200)", languages.toArray()));
        statement.executeUpdate();
    }

    private void insertTools(Connection connection, int id, boolean exclude) throws SQLException {
        String flipper = exclude ? "NOT" : "";
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO orders_tools (order_id, tool_id)\n" +
                        "\tSELECT ?, id FROM tools WHERE tools.tool " + flipper + " IN ?\n" +
                        "ON CONFLICT DO NOTHING;"
        );
        statement.setInt(1, id);
        statement.setArray(2, connection.createArrayOf("VARCHAR(200)", tools.toArray()));
        statement.executeUpdate();
    }

    private void deleteTools(Connection connection, boolean exclude) throws SQLException {
        String flipper = exclude ? "NOT" : "";
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders_tools WHERE order_id = ? AND tool_id IN (\n" +
                        "\tSELECT id FROM tools WHERE tools.tool " + flipper + " IN ?\n" +
                        ");"
        );
        statement.setInt(1, id);
        statement.setArray(2, connection.createArrayOf("VARCHAR(200)", tools.toArray()));
        statement.executeUpdate();
    }


    private int insertBody(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO orders (name, salary, education_requirement) VALUES (? ? ?);"
        );
        statement.setString(1, name);
        statement.setInt(2, salary);
        statement.setString(3, educationRequirement.name());
        statement.setBoolean(4, closed);
        statement.execute();
        ResultSet generateds = statement.getGeneratedKeys();
        generateds.next();
        return generateds.getInt("id");
    }

    private void deleteBody(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM orders WHERE id = ?;"
        );
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void updateBody(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE orders SET name = ?, salary = ?, education_requirement = ? WHERE id = ?;"
        );
        statement.setString(1, name);
        statement.setInt(2, salary);
        statement.setString(3, educationRequirement.name());
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    public void insert(Connection connection) throws SQLException {
        // the id will be dynamically generated
        // add the body because the associated tables will reference it
        // retrieve the generated id
        int id = insertBody(connection);
        // insert into associated tables
        insertLanguages(connection, id, false);
        insertTools(connection, id, false);
    }

    public void delete(Connection connection) throws SQLException {
        // the id is given in the record
        // delete any associated values connected to id
        deleteLanguages(connection, false);
        deleteTools(connection, false);
        // delete row connected to id
        deleteBody(connection);
    }

    public void update(Connection connection) throws SQLException {
        // the id is given in the record
        // update the main row of the record with all values
        updateBody(connection);
        // delete any associated values not in the id
        deleteLanguages(connection, true);
        deleteTools(connection, true);
        // insert associated values (the tables will reject duplicates)
        insertLanguages(connection, id,false);
        insertTools(connection, id, false);
    }
}
