package app.user;

import app.helpers.JwtHelper;
import app.user.DTOs.UserCredentialsDto;
import app.user.userRepository.UserRepository;
import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class UserController {

    private final JwtHelper jwtHelper;
    Gson gson;
    UserRepository userRepository;
  

    public UserController(JwtHelper jwtHelper, Gson gson, UserRepository userRepository){
        this.jwtHelper = jwtHelper;
        this.gson = gson;
        this.userRepository = userRepository;
    }

    public void logIn(Context ctx) throws SQLException {
        UserCredentialsDto userCredentialsDto = gson.fromJson(ctx.body(), UserCredentialsDto.class);

        if (userCredentialsDto.password == null) {
            throw new HttpResponseException(400, "Missing password");
        }

        if (userCredentialsDto.email == null) {
            throw new HttpResponseException(400, "Missing email");
        }

        User user = userRepository.getUserByEmail(userCredentialsDto.email);
        if (user == null) {
            throw new HttpResponseException(400, "User not found");
        }

        String hashedPassword = BCrypt.hashpw(userCredentialsDto.password, user.salt);

        boolean areUsersCredentialsValid = hashedPassword.equals(user.password);


        if (!areUsersCredentialsValid) {
            throw new HttpResponseException(400, "Bad credentials");
        }

        ctx.json(jwtHelper.generateToken(user.getId()));
    }
    public void signUp(Context ctx) throws SQLException {
        UserCredentialsDto userCredentialsDto = gson.fromJson(ctx.body(), UserCredentialsDto.class);

        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(userCredentialsDto.password, salt);
        User savedUser = userRepository.saveOneUser(new User(userCredentialsDto.username, salt, hashedPassword, userCredentialsDto.email));
        System.out.println(salt);
        System.out.println(hashedPassword);
        ctx.json(jwtHelper.generateToken(savedUser.getId()));
    }


}
