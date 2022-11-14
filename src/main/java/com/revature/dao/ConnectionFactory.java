package com.revature.dao;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Deprecated
public interface ConnectionFactory {

    /*
    static String url = System.getenv("DB_URL");
    static String username = System.getenv("DB_USER");
    static String password = System.getenv("DB_PASS");
    */

    static Connection createConnection() throws SQLException {

        String url = "jdbc:postgresql://34.70.254.221:5432/postgres";
        String username = "postgres";
        String password = "Password123";

        /*String url = System.getenv("pizza_url");
        String username = System.getenv("pizza_user");
        String password = System.getenv("pizza_pass");*/

        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}
