package com.revature.dao;

import com.revature.data.records.Template;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TemplatesDao extends Dao {
    public static List<Template> getTemplates() throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM premades;");
            ResultSet results = statement.executeQuery();
            return Template.fromAll(results);
        }
    }
}
