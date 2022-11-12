package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionFactory {

    static String url = System.getenv("DB_URL");
    static String username = System.getenv("DB_USER");
    static String password = System.getenv("DB_PASS");

    static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
