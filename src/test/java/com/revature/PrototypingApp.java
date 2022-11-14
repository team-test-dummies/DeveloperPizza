package com.revature;

import com.revature.dao.Dao;

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
        Main.initialize().start();
    }

    // used inside the test classes (should probably be moved to its own class
    private static String acc;

    // used inside the test classes (should probably be moved to its own class
    public static void setup() throws SQLException {
        // put og url in acc
        acc = Dao.url;
        // set database to h2
        Dao.url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; // prevents the DB from clearing upon last connection close
        // fill database with test data
        try (Connection connection = DriverManager.getConnection(Dao.url)) {
            SQLFetcher fetcher = new SQLFetcher(connection);
            PreparedStatement create = fetcher.fetch(
                    "sql/create.psql" // sql fetcher cannot handle more than one file
            );
            create.execute();
        }
    }

    // used inside the test classes (should probably be moved to its own class
    public static void cleanup() throws SQLException {
        // empty h2 database
        try (Connection connection = DriverManager.getConnection(Dao.url)) {
            PreparedStatement destroy = connection.prepareStatement("DROP ALL OBJECTS;");
            destroy.execute();
        }
        // reset to whatever database came before
        Dao.url = acc;
    }
}