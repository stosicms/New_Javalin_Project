package app.user;

import app.helpers.JwtHelper;
import app.user.DTOs.UserCredentialsDto;
import com.google.gson.Gson;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    private final JwtHelper jwtHelper;
    Gson gson = new Gson();

    UserDao userDao = UserDao.getInstance();

  

    public UserController(JwtHelper jwtHelper){
        this.jwtHelper = jwtHelper;
    }

    public void logIn(Context ctx) {
        UserCredentialsDto userCredentialsDto = gson.fromJson(ctx.body(), UserCredentialsDto.class);

        if (userCredentialsDto.username == null || userCredentialsDto.password == null) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "Missing username or password");
            ctx.status(400).json(model);
            return;
        }

        User user = userDao.getUserByUsername(userCredentialsDto.username);
        if (user == null) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "User not found");
            ctx.status(400).json(model);
            return;
        }

        String hashedPassword = BCrypt.hashpw(userCredentialsDto.password, user.salt);

        boolean areUsersCredentialsValid = hashedPassword.equals(user.password);


        if (!areUsersCredentialsValid) {
            Map<String, String> model = new HashMap<>();
            model.put("error", "Bad credentials");
            ctx.status(400).json(model);
            return;
        }

        ctx.json(jwtHelper.generateToken(userCredentialsDto.username));
    }
    public void signUp(Context ctx) {
        UserCredentialsDto userCredentialsDto = gson.fromJson(ctx.body(), UserCredentialsDto.class);

        String salt = BCrypt.gensalt();

        String hashedPassword = BCrypt.hashpw(userCredentialsDto.password, salt);
        User newUser = new User(userCredentialsDto.username, salt, hashedPassword);
        userDao.saveOneUser(newUser);
        System.out.println(salt);
        System.out.println(hashedPassword);
        ctx.json(jwtHelper.generateToken(userCredentialsDto.username));
    }


}
