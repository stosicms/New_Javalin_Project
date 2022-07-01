package app.user;

import app.user.DTOs.UserCredentialsDto;
import com.google.gson.Gson;
import io.javalin.http.Context;
import io.jsonwebtoken.Jwts;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    Gson gson = new Gson();

    UserDao userDao = UserDao.getInstance();

    private Map<String, String> generateToken(String username) {
        Map<String, String> model = new HashMap<>();

        String jwtToken = Jwts.builder()
                .claim("username", username)
                .compact();
        model.put("response", jwtToken);
        return model;
    }

    public Context logIn(Context ctx) {
        UserCredentialsDto userCredentialsDto = gson.fromJson(ctx.body(), UserCredentialsDto.class);


        if (userCredentialsDto.username == null || userCredentialsDto.password == null) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "Missing username or password");
            return ctx.status(400).json(model);
        }

        User user = userDao.getUserByUsername(userCredentialsDto.username);
        if (user == null) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "User not found");
            return ctx.status(400).json(model);
        }

        String hashedPassword = BCrypt.hashpw(userCredentialsDto.password, user.salt);

        boolean areUsersCredentialsValid = hashedPassword.equals(user.password);

        if (!areUsersCredentialsValid) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "Bad credentials");
            return ctx.status(400).json(model);
        }

        return ctx.status(200).json(generateToken(userCredentialsDto.username));
    }
    public Context signUp(Context ctx) {
        UserCredentialsDto userCredentialsDto = gson.fromJson(ctx.body(), UserCredentialsDto.class);

        String salt = BCrypt.gensalt();

        String hashedPassword = BCrypt.hashpw(userCredentialsDto.password, salt);
        User newUser = new User(userCredentialsDto.username, salt, hashedPassword);
        userDao.saveOneUser(newUser);

        return ctx.status(200).json(generateToken(userCredentialsDto.username));
    }


}
