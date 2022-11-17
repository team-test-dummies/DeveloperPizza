package com.revature.dao;

import com.revature.data.records.Template;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemplatesDao extends Dao {

    private static List<String> parseAll(ResultSet results, String field) throws SQLException {
        List<String> parsed = new ArrayList<>();
        while(results.next()) {
            parsed.add(results.getString(field));
        }
        return Collections.unmodifiableList(parsed);
    }

    public static List<Template> getTemplates() throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM premades;"
            );
            ResultSet results = statement.executeQuery();
            return Template.fromAll(results);
        }
    }

    public static List<String> getLanguages() throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM languages;"
            );
            ResultSet results = statement.executeQuery();
            return parseAll(results, "language");
        }
    }

    public static List<String> getTools() throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM tools;"
            );
            ResultSet results = statement.executeQuery();
            return parseAll(results, "tool");
        }
    }
}
