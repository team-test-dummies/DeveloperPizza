package com.revature.controller;

import com.revature.exception.AuthorizationException;
import com.revature.exception.ValidationException;
import com.revature.records.Authority;
import com.revature.records.Credentials;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import jakarta.servlet.http.HttpSession;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class AuthController implements Controller {

    public static void login(Context context) {
        Credentials credentials = context.bodyAsClass(Credentials.class);
        try {
            // make sure both username and password are given
            UserService.validate(credentials);
            // sanitize by converting credentials to lowercase
            credentials = UserService.sanitize(credentials);
            // authentication() will throw a login exception if credentials are invalid
            Authority authority = UserService.authenticate(credentials);
            // change session id to prevent session fixation
            try {
                context.req().changeSessionId();
            }
            catch (IllegalStateException e) {} // if no session exists one will be created
            // add authority to session for future use
            context.req().getSession().setAttribute("authority", authority);
            // now we authorize the changed session
            context.status(HttpStatus.NO_CONTENT);
        }
        catch (AuthorizationException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
        catch (ValidationException e) {
            context.status(HttpStatus.BAD_REQUEST);
        }
        catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void logout(Context context) {
        throw new Error("unimplemented");
    }
}