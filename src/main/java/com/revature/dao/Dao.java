package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Dao {

    public static String url = System.getenv("DB_URL");
    public static String username = System.getenv("DB_USER");
    public static String password = System.getenv("DB_PASS");
    public static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
