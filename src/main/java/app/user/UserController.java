package app.user;

import io.javalin.http.Context;

import java.util.List;

public class UserController {
    static UserDao userDao = new UserDao();

    public static void getAllUsers(Context context) {
        List<User> result = userDao.getAllUsers();
        context.json(result);
    }

    public static void getOneUser (Context context) {
        String username = context.pathParam("username");
        User result = userDao.getUserByUsername(username);
        context.json(result);
    }
}
