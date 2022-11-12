package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLFetcher {

    private static Scanner scanner;
    private static StringBuffer buffer;
    private static Connection connection;

    SQLFetcher(Connection connection) {
        this.connection = connection;
    }
    public PreparedStatement fetch(String resourceUrl) throws SQLException {
        buffer = new StringBuffer();
        scanner = new Scanner(
                getClass().getClassLoader().getResourceAsStream(resourceUrl)
        );
        while (scanner.hasNextLine()) {
            buffer.append(scanner.nextLine());
            buffer.append("\n");
        }
        return connection.prepareStatement(
                buffer.toString()
        );
    }
}
