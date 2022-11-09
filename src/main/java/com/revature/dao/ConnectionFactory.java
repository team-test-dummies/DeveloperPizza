package com.revature.dao;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {
        // Setup Connection With DB

        // Setup the environment file 'Jeremy' was talking about
        String url = System.getenv("db_url");
        String username = System.getenv("db_user");
        String password = System.getenv("db_pass");

        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
