package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exception.AuthorizationException;
import com.revature.exception.ValidationException;
import com.revature.records.Authority;
import com.revature.records.Credentials;
import com.revature.records.UserDto;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Arrays;

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
    public static Credentials sanitize(Credentials credentials) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // we don't want to include any accidental spaces in username
        // we don't want someone claiming 'FaMouS PeRsOn To ImPeRSonate'
        String username = credentials.username().trim().toLowerCase();
        // we don't want to store unsalted+hashed passwords
        String password = quickhash(username, credentials.password());
        return new Credentials(
            username,
            password
        );

    }

    // because usernames are unique, using a username as a salt should do fine
    public static String quickhash(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] nonce = new byte[] {78, -113, 16, 20, -45, 92, -115, -37, -72, 40, 65, -29, 34, 104, -34, -80, 42, 78, -91, 101};
        byte[] usernonce = username.getBytes();
        byte[] salt = Arrays.copyOf(usernonce, usernonce.length + nonce.length);
        for (int i = nonce.length; i < nonce.length; i++) {
            salt[usernonce.length + i] = nonce[i];
        }
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        // pulled from the internet, should be double checked by expert
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        // output appears to be hashed, probably want to keep this out of the string pool, but it is better than nothing
        return new String(hash);
    }
}
