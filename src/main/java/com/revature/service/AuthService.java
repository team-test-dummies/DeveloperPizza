package com.revature.service;

import com.revature.dao.AuthDao;
import com.revature.data.exception.AuthorizationException;
import com.revature.data.exception.ValidationException;
import com.revature.data.records.Authority;
import com.revature.data.records.Credentials;
import com.revature.data.records.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class AuthService {

    public static Authority authenticate(Credentials credentials) throws SQLException, AuthorizationException, NoSuchAlgorithmException, InvalidKeySpecException {
        User userDTO = AuthDao.findUser(credentials);
        if (userDTO == null) throw new AuthorizationException("Invalid Login");
        else {
            return new Authority(
                    userDTO.id(),
                    userDTO.accountType()
            );
        }
    }

    // remember we can overload validate(Other type) as much as we want
    static Pattern solidUsername = Pattern.compile("^\\s*[A-Za-z0-9]+\\s*$");
    public static void validate(Credentials credentials) throws ValidationException {
        // both fields must be present
        if (
            credentials.username() == null ||
            credentials.password() == null ||
            credentials.username().length() == 0 ||
            credentials.password().length() == 0
        ) throw new ValidationException("credentials field was left empty");
        // and username should be shorter than 16 characters
        if (
            credentials.username().length() > 16
        ) throw new ValidationException("username too long");
        // we may want to restrict usernames to [a-Z0-9] but we can expect sanitize to trim whitespace
        if (
                !solidUsername.matcher(credentials.username()).find()
        ) throw new ValidationException("username contains invalid characters");
    }

    // remember we can overload sanitize(Other type) as much as we want
    public static Credentials sanitize(Credentials credentials) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // we don't want to include any accidental spaces in username
        // we don't want someone claiming 'FaMouS PeRsOn To ImPeRSonate'
        String username = credentials.username().trim().toLowerCase();
        return new Credentials(
            username,
            credentials.password()
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
