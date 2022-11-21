package com.revature;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class SQLFetcher {

    private Connection connection;
    public SQLFetcher(Connection connection) {
        this.connection = connection;
    }
    public PreparedStatement prepareStatement(String resourceUrl) throws SQLException, IOException {
        URL resource = getClass().getClassLoader().getResource(resourceUrl);
        try (FileReader reader = new FileReader(resource.getFile())) {
        try (BufferedReader buffered = new BufferedReader(reader)) {
            String output = buffered.lines().collect(Collectors.joining("\n"));
            return connection.prepareStatement(output);
        }
        }
    }
}
