package com.revature.controller;


import com.revature.dao.AuthDao;
import com.revature.data.exception.AuthorizationException;
import com.revature.data.records.Authority;
import com.revature.data.records.Credentials;
import com.revature.service.AuthService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.openapi.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class AuthController {

    @OpenApi(
            summary = "Request a session cookie for authorization",
            operationId = "login",
            path = "/login",
            methods = HttpMethod.POST,
            tags = "Auth",
            requestBody = @OpenApiRequestBody(content = @OpenApiContent(from = Credentials.class)),
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void login(Context context) {
        try {
            Credentials credentials = context.bodyAsClass(Credentials.class);
            // make sure both username and password are given
            AuthService.validate(credentials);
            // sanitize by converting credentials to lowercase
            credentials = AuthService.sanitize(credentials);
            // authentication() will throw a login exception if credentials are invalid
            Authority authority = AuthService.authenticate(credentials);
            // change session id to prevent session fixation
            AuthDao.findUser(credentials);
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
        catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // for some reason I cannot catch the DataBindException any other way
        catch (Exception e) {
            context.status(HttpStatus.BAD_REQUEST);
        }
    }

    @OpenApi(
            summary = "Invalidate the users login session cookie",
            operationId = "logout",
            path = "/logout",
            methods = HttpMethod.GET,
            tags = "Auth",
            responses = {
                    @OpenApiResponse(status = "204"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void logout(Context context) {

        context.req().getSession().invalidate();
        context.status(HttpStatus.NO_CONTENT);
    }

    @OpenApi(
            summary = "Retrieve the current user's authority",
            operationId = "whoami",
            path = "/whoami",
            tags = "Auth",
            methods = HttpMethod.GET,
            responses = {
                    @OpenApiResponse(status = "200", content = @OpenApiContent(from = Authority.class)),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void whoami(Context context) {
        try {
            Authority authority = context.sessionAttribute("authority");
            context.json(authority);
        }
        catch (Exception e) {
            context.status(HttpStatus.BAD_REQUEST);
        }
    }

}
