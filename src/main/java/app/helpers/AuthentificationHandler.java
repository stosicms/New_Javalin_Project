package app.helpers;

import app.user.User;
import app.user.UserDao;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpResponseException;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwt;

import java.util.HashMap;
import java.util.Map;

public class AuthentificationHandler {
    private final JwtHelper jwtHelper;
    private final UserDao userDao;

    public AuthentificationHandler (JwtHelper jwtHelper, UserDao userDao) {
        this.userDao = userDao;
        this.jwtHelper = jwtHelper;
    }
    public void validateUser(Context ctx) {


        String authorizationHeaderWithBearerPrefix = ctx.header("Authorization");

        if (authorizationHeaderWithBearerPrefix == null) {
            throw new HttpResponseException(403, "missing token");
        }

        try {
            String[] TokenArray = authorizationHeaderWithBearerPrefix.split(" ");
            Jwt<JwsHeader, Claims> decodedToken =  jwtHelper.parseToken( TokenArray[1]);
            Object username = decodedToken.getBody().get("username");
            User user =  userDao.getUserByUsername((String) username);
            if (user.username == null) {
                throw new HttpResponseException(403, "invalid token");
            }

            ctx.attribute("user", user);

        } catch (HttpResponseException e) {
            throw new HttpResponseException(403, "invalid token");
        }

    }
}
