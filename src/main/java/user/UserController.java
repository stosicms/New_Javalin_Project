package user;
import io.javalin.http.Context;
import java.util.List;

public class UserController {
    static UserDao  userDao = new UserDao();
    
    
    public static void getAllUsers(Context ctx){
        List<User> result = userDao.getAllUsers();
        ctx.json(result);
    }

    public static void getOneUser(Context ctx){
        String username = ctx.pathParam("username");
        User result = userDao.GetUserByName(username);
        ctx.json(result);
    }
}
