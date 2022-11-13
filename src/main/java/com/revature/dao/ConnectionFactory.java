package com.revature.dao;
<<<<<<< HEAD
import org.postgresql.Driver;
=======
>>>>>>> main

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

<<<<<<< HEAD
public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {

        // Setup the environment file 'Jeremy' was talking about DB_URL, DB_USER, DB_PASS
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");

        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);
=======
public interface ConnectionFactory {

    static String url = System.getenv("DB_URL");
    static String username = System.getenv("DB_USER");
    static String password = System.getenv("DB_PASS");
>>>>>>> main

    static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
