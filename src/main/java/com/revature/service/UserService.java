package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exception.AuthorizationException;
import com.revature.exception.ValidationException;
import com.revature.records.Authority;
import com.revature.records.Credentials;
import com.revature.records.UserDto;

import java.sql.SQLException;

public class UserService {
    public static Authority authenticate(Credentials credentials) throws SQLException, AuthorizationException  {
        UserDto userDTO = UserDao.findUser(credentials);
        if (userDTO == null) throw new AuthorizationException("Invalid Login");
        else {
            return new Authority(
                    userDTO.id(),
                    userDTO.accountType()
            );
        }
    }

    // remember we can overload validate(Other type) as much as we want
    public static void validate(Credentials credentials) throws ValidationException {
        // both fields must be present
        if (
            credentials.username() == null ||
            credentials.password() == null ||
            credentials.username().length() == 0 ||
            credentials.password().length() == 0
        ) throw new ValidationException();
        // we may want to restrict usernames to [a-Z0-9]
    }

    // remember we can overload sanitize(Other type) as much as we want
    public static Credentials sanitize(Credentials credentials) {
        // we don't want someone claiming 'FaMouS PeRsOn To ImPeRSonate'
        return new Credentials(
            credentials.username().toLowerCase(),
            credentials.password()
        );
    }
}
