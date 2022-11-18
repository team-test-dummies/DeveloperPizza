package com.revature.dao;

import com.revature.data.records.Languages;
import com.revature.data.records.Premades;
import com.revature.data.records.StartOrderItems;
import com.revature.data.records.Tools;

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
    private static PreparedStatement selectAllPremades(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM premades");
    }
    private static PreparedStatement selectAllTools(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * from tools");
    }
    public static List<StartOrderItems> startOrderList() throws SQLException {

        List<StartOrderItems> startOrderItems = new ArrayList<>();
        startOrderItems.add(new StartOrderItems(listLanguages(), listTools(), listPremades()));
        return startOrderItems;
    }
    private static List<Languages> listLanguages() throws SQLException {
        try(Connection connection = createConnection()) {
            ResultSet result = selectAllLanguages(connection).executeQuery();
            List<Languages> language= new ArrayList<>();
            while (result.next()) {
                language.add(new Languages(
                        result.getString("language"))
                );
            }
            return language;
        }
    }
    private static List<Tools> listTools() throws SQLException {
        try(Connection connection = createConnection()) {
            ResultSet result = selectAllTools(connection).executeQuery();
            List<Tools> tools= new ArrayList<>();
            while (result.next()) {
                tools.add(new Tools(
                        result.getString("tool"))
                );
            }
            return tools;
        }
    }
    private static List<Premades> listPremades() throws SQLException {
        try(Connection connection = createConnection()) {
            ResultSet result = selectAllPremades(connection).executeQuery();
            List<Premades> premades= new ArrayList<>();
            while (result.next()) {
                premades.add(new Premades(
                        result.getString("premade"))
                );
            }
            return premades;
        }
    }
}
