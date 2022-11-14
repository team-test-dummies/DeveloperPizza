package com.revature.controller;

import com.revature.dao.UserDao;
import com.revature.exception.AuthorizationException;
import com.revature.exception.ValidationException;
import com.revature.records.Authority;
import com.revature.records.Credentials;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;


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
            UserDao.findUser(credentials);
            try {
                context.req().changeSessionId();
            }
            catch (IllegalStateException ignored) {} // if no session exists one will be created
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
        catch (SQLException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logout(Context context) {
        throw new Error("unimplemented");
    }

    @Deprecated
    public void mapEndpoint(Javalin app) {
        app.post("/login", AuthController::login);
    }
}
