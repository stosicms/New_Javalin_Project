package app.user;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.jsonwebtoken.Jwts;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserController {

    static Gson gson = new Gson();

    static UserDao userDao = UserDao.getInstance();

    public static Context logIn(Context ctx) {
        String requestBodyAsString = ctx.body();
        User requestBodyAsJson = gson.fromJson(requestBodyAsString, User.class);


        if (requestBodyAsJson.username == null || requestBodyAsJson.password == null) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "Missing username or password");
            return ctx.status(400).json(model);
        }

        User user = userDao.getUserByUsername(requestBodyAsJson.username);
        if (user == null) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "User not found");
            return ctx.status(400).json(model);
        }

        String hashedPassword = BCrypt.hashpw(requestBodyAsJson.password, user.salt);

        boolean areUsersCredentialsValid = hashedPassword.equals(user.password);

        if (!areUsersCredentialsValid) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "Bad credentials");
            return ctx.status(400).json(model);
        }

        Map<String, String> model = new HashMap<>();

        String jwtToken = Jwts.builder()
                .claim("username", requestBodyAsJson.username)
                .setId(UUID.randomUUID().toString())
                .compact();
        // todo kako generisati token
        model.put("response", jwtToken);
        return ctx.status(200).json(model);
    }


}
