package com.revature.dao;

import com.revature.records.StartOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StartOrderDao extends Dao {
    private static PreparedStatement selectAllLanguages(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM languages");
    }
    private static PreparedStatement selectAllTools(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM tools");
    }

    public static List<StartOrder> listLanguages() throws SQLException {
        try(Connection connection = createConnection()) {
            ResultSet result = selectAllLanguages(connection).executeQuery();
            List<StartOrder> topping= new ArrayList<>();
            while (result.next()) {
                topping.add(new StartOrder(
                        result.getString("topping"))
                );
            }
            return topping;
        }
    }
    public static List<StartOrder> listTools() throws SQLException {
        try(Connection connection = createConnection()) {
            ResultSet result = selectAllTools(connection).executeQuery();
            List<StartOrder> tools= new ArrayList<>();
            while (result.next()) {
                tools.add(new StartOrder(
                        result.getString("topping"))
                );
            }
            return tools;
        }
    }
}
