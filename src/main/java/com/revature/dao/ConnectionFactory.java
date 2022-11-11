package com.revature.dao;

import com.revature.Main;
import org.postgresql.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Stack;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException, IOException {
        InputStream props = Main.class.getClassLoader().getResourceAsStream("database_config.properties");
        Properties properties = new Properties();
        boolean useH2 = Boolean.parseBoolean(properties.getProperty("use-h2"));

        if (useH2) {
            return DriverManager.getConnection("jdbc:h2:mem:test");
        } else {
            String url = "";
            String username = "";
            String password = "";

            return DriverManager.getConnection(url, username, password);
        }
    }

    public static void populatedH2Database(Connection con) throws SQLException, IOException {
        String createTableSql = "create table orders (\n" +
                "\tid SERIAL primary key,\n" +
                "\tskillset VARCHAR(200) not null,\n" +
                "\tlocation VARCHAR(200) not null,\n" +
                "\tavailability VARCHAR(200) not null,\n" +
                "\tsalary VARCHAR(200) not null,\n" +
                "\texperience VARCHAR(200) not null,\n" +
                "\teducation VARCHAR(200) not null,\n" +
                "\tcertifications VARCHAR(200) not null,\n" +
                "\tlanguages VARCHAR(200) not null,\n" +
                "\tframeworks VARCHAR(200) not null,\n" +
                "\tdatabases VARCHAR(200) not null,\n" +
                "\toperatingsystems VARCHAR(200) not null,\n" +
                "\ttools VARCHAR(200) not null,\n" +
                "\thobbies VARCHAR(200) not null,\n" +
                ")";

        String insertOrders = "insert into orders (skillset, location, availability, salary, experience, education, certifications, languages, frameworks, databases, operatingsystems, tools, hobbies)\n" +
                "values \n" +
                "('Technology', 'Texas', 'Re-Location', '80,000', 'Bachelors', 'AWS-Certified', 'Java', 'NIST', 'Microsoft Access', 'Linux', 'IntelliJJ', 'Dodgeball'),\n" +
                "('Cloud', 'Minnesota', 'Remote', '40,000', 'Bachelors', 'SQL-Certified', 'Python', 'SDLC', 'PostgreSQL', 'MacOS', 'Visual Studio', 'Youtuber'),\n" +
                "('Programming', 'Florida', 'In-State', '60,000', 'Masters', 'NULL', 'Ruby', 'OWASP', 'Azure', 'Windows', 'Notepad++', 'Artist')";

        PreparedStatement ps1 = con.prepareStatement(createTableSql);
        ps1.executeUpdate();

        PreparedStatement ps2 = con.prepareStatement(insertOrders);
        ps2.executeUpdate();
    }

    public static void clearH2Database(Connection con) throws SQLException {
        String sql = "DROP ALL OBJECTS";

        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
    }
}
