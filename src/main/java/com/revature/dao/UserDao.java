package com.revature.dao;

import com.revature.records.Credentials;
import com.revature.records.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {

    private static final PreparedStatement selectByCredentials(Connection connection, String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?;");
        statement.setString(1, username);
        statement.setString(2, password);
        return statement;
    }

    public static final UserDto findUser(Credentials credentials) throws SQLException {
        try (Connection connection = createConnection()) {
            ResultSet result = selectByCredentials(
                    connection,
                    credentials.username(),
                    credentials.password()
            ).executeQuery();
            if (result.next()) return UserDto.parse(result);
            else return null;
        }
    }
}
