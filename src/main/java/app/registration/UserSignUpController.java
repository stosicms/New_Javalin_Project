package app.registration;

import app.user.User;
import app.user.UserDao;
import com.google.gson.Gson;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UserSignUpController {
    static UserDao userDao = UserDao.getInstance();
    static Gson gson = new Gson();

    public static void getAllUsers(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("result", userDao.getAllUserNames());
        ctx.json(model);
    }
    public static Context signUp(Context ctx) {

        String requestBodyAsString = ctx.body();
        User requestBodyAsJson = gson.fromJson(requestBodyAsString, User.class);

        String salt = BCrypt.gensalt();

        String hashedPassword = BCrypt.hashpw(requestBodyAsJson.password, salt);

        User newUser = new User(requestBodyAsJson.username, salt, hashedPassword);

        userDao.saveOneUser(newUser);
        User opetUser = userDao.getUserByUsername(requestBodyAsJson.username);

        Map<String, Iterable<String>> model = new HashMap<>();

        // todo kako generisati token
        model.put("response", userDao.getAllUserNames());
        return ctx.status(200).json(model);
    }
}
