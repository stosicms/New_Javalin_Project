package app.user;

import app.helpers.JwtHelper;
import app.user.DTOs.UserCredentialsDto;
import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    private final JwtHelper jwtHelper;
    Gson gson;
    UserDao userDao;

  

    public UserController(JwtHelper jwtHelper, Gson gson, UserDao userDao){
        this.jwtHelper = jwtHelper;
        this.gson = gson;
        this.userDao = userDao;
    }

    public void logIn(Context ctx) {
        UserCredentialsDto userCredentialsDto = gson.fromJson(ctx.body(), UserCredentialsDto.class);

        if (userCredentialsDto.username == null || userCredentialsDto.password == null) {
            throw new HttpResponseException(400, "Missing username or password");
        }

        User user = userDao.getUserByUsername(userCredentialsDto.username);
        if (user == null) {
            throw new HttpResponseException(400, "User not found");
        }

        String hashedPassword = BCrypt.hashpw(userCredentialsDto.password, user.salt);

        boolean areUsersCredentialsValid = hashedPassword.equals(user.password);


        if (!areUsersCredentialsValid) {
            throw new HttpResponseException(400, "Bad credentials");
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
