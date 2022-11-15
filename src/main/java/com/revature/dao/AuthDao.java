package com.revature.dao;

import com.revature.data.records.Credentials;
import com.revature.data.records.User;
import com.revature.service.AuthService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao extends Dao {

    private static final PreparedStatement selectByCredentials(Connection connection, String username, String password) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = AuthService.quickhash(username, password);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?;");
        statement.setString(1, username);
        statement.setString(2, hashedPassword);
        return statement;
    }

    public static final User findUser(Credentials credentials) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        try (Connection connection = createConnection()) {
            ResultSet result = selectByCredentials(
                    connection,
                    credentials.username(),
                    credentials.password()
            ).executeQuery();
            if (result.next()) return User.parse(result);
            else return null;
        }
    }
}
