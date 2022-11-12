package com.revature.dao;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {
        // Dotenv object created

        // Setup Connection With DB

        // Setup the environment file 'Jeremy' was talking about DB_URL, DB_USER, DB_PASS
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");

        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
        return connection;
    }
}
