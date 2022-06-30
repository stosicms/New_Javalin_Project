package app.user;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.jsonwebtoken.Jwts;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    Gson gson = new Gson();

    UserDao userDao = UserDao.getInstance();

    public Context logIn(Context ctx) {
        String requestBodyAsString = ctx.body();
        User requestBodyAsJson = gson.fromJson(ctx.body(), User.class);


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


        return ctx.status(200).json(generateToken(requestBodyAsJson.username));
    }
    private Map<String, String> generateToken(String username) {
        Map<String, String> model = new HashMap<>();

        String jwtToken = Jwts.builder()
                .claim("username", username)
                .compact();
        model.put("response", jwtToken);
        return model;
    }
    public Context signUp(Context ctx) {
        String requestBodyAsString = ctx.body();
        User requestBodyAsJson = gson.fromJson(requestBodyAsString, User.class);

        String salt = BCrypt.gensalt();

        String hashedPassword = BCrypt.hashpw(requestBodyAsJson.password, salt);
        User newUser = new User(requestBodyAsJson.username, salt, hashedPassword);
        userDao.saveOneUser(newUser);

        return ctx.status(200).json(generateToken(requestBodyAsJson.username));
    }


}
