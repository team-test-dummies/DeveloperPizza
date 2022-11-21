package com.revature;

import com.revature.dao.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrototypingApp {

    // launches a server for exploratory testing
    public static void main(String[] args) throws SQLException {
        // create test data
        setup();
        // start the server on run
        App.initialize().start();
    }

    // used inside the test classes (should probably be moved to its own class
    private static String accUrl;
    private static String accUsername;
    private static String accPassword;

    // used inside the test classes (should probably be moved to its own class
    public static void setup() {
        // put og url in acc
        accUrl = Dao.url;
        accUsername = Dao.username;
        accPassword = Dao.password;
        // set database to h2
        Dao.url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; // prevents the DB from clearing upon last connection close
        Dao.username = null;
        Dao.password = null;
        // fill database with test data
        try (Connection connection = DriverManager.getConnection(Dao.url)) {
            SQLFetcher fetcher = new SQLFetcher(connection);
            PreparedStatement create = fetcher.prepareStatement(
                    "sql/create.psql" // sql fetcher cannot handle more than one file
            );
            create.execute();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // used inside the test classes (should probably be moved to its own class
    public static void cleanup() throws SQLException {
        // empty h2 database
        try (Connection connection = DriverManager.getConnection(Dao.url)) {
            PreparedStatement destroy = connection.prepareStatement("DROP ALL OBJECTS;");
            destroy.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // reset to whatever database came before
        Dao.url = accUrl;
        Dao.username = accUsername;
        Dao.password = accPassword;
    }
}
