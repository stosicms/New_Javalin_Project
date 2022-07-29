package app.helpers;

import app.user.User;
import app.user.UserDao;
import app.user.userRepository.UserRepository;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpResponseException;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwt;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthentificationHandler {
    private final JwtHelper jwtHelper;
    private final UserRepository userRepository;

    public AuthentificationHandler (JwtHelper jwtHelper, UserRepository userRepository) {
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
    }
    public void validateUser(Context ctx) {


        String authorizationHeaderWithBearerPrefix = ctx.header("Authorization");

        if (authorizationHeaderWithBearerPrefix == null) {
            throw new HttpResponseException(403, "missing token");
        }

        try {
            String[] tokenArray = authorizationHeaderWithBearerPrefix.split(" ");
            Jwt<JwsHeader, Claims> decodedToken =  jwtHelper.parseToken( tokenArray[1]);
            Object userId = decodedToken.getBody().get("userId");
            User user =  userRepository.getUserById((String) userId);
            if (user.id == null) {
                throw new HttpResponseException(403, "invalid token");
            }

            ctx.attribute("user", user);

        } catch (HttpResponseException | SQLException e) {
            e.printStackTrace();
            throw new HttpResponseException(403, "invalid token");
        }

    }
}
