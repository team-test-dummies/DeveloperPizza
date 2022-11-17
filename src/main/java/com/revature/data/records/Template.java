package com.revature.data.records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Template(String name, List<String> languages, List<String> tools) {

    private static List<String> getLanguages(Connection connection, int templateID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT languages.language FROM premades_languages\n" +
                        "LEFT OUTER JOIN languages ON premades_languages.language_id = languages.id\n" +
                        "WHERE premades_languages.premade_id = ?;"
        );
        statement.setInt(1, templateID);
        ResultSet results = statement.executeQuery();
        List<String> languages = new ArrayList<>();
        while(results.next()) {
            languages.add(results.getString("language"));
        }
        return Collections.unmodifiableList(languages);
    }

    private static List<String> getTools(Connection connection, int templateID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT tools.tool FROM premades_tools\n" +
                        "LEFT OUTER JOIN tools ON premades_tools.tool_id = tools.id\n" +
                        "WHERE premades_tools.premade_id = ?;"
        );
        statement.setInt(1, templateID);
        ResultSet results = statement.executeQuery();
        List<String> tools = new ArrayList<>();
        while(results.next()) {
            tools.add(results.getString("tool"));
        }
        return Collections.unmodifiableList(tools);
    }

    public static Template from(ResultSet result) throws SQLException {
        int templateID = result.getInt("id");
        String name = result.getString("premade");
        Connection connection = result.getStatement().getConnection();
        List<String> languages = getLanguages(connection, templateID);
        List<String> tools = getTools(connection, templateID);
        return new Template(name, languages, tools);
    }

    public static List<Template> fromAll(ResultSet results) throws SQLException {
        List<Template> templates = new ArrayList<>();
        while(results.next()) {
            templates.add(Template.from(results));
        }
        return Collections.unmodifiableList(templates);
    }

}
