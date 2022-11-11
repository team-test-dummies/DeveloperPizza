package com.revature.dao;

import io.github.cdimascio.dotenv.Dotenv;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {
        // Dotenv object created
        Dotenv dotenv = Dotenv.configure().directory("./src/").load();
        // Setup Connection With DB

        // Setup the environment file 'Jeremy' was talking about
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASS");

        Driver postgresDriver = new Driver();
        DriverManager.registerDriver(postgresDriver);

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
        return connection;
    }
}
