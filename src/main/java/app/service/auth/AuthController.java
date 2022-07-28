package app.service.auth;

import app.user.User;
import app.user.UserDao;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import org.mindrot.jbcrypt.BCrypt;

public class AuthController {
    JWTService jwtService;
    UserDao userDao = new UserDao();

    public AuthController () {
        this.jwtService = new JWTService();
    }

    public void loginUser (Context context) {
        User user = context.bodyAsClass(User.class);
        User dbUser = userDao.getUserByUsername(user.getUsername());

        boolean isValid = BCrypt.checkpw(user.getPassword(), dbUser.getPassword());

        if (isValid) {
            String token = jwtService.generateJWT();
            context.cookie("codeDNA", token).result("Login successful");
        }
        else {
            throw new BadRequestResponse("Invalid credentials");
        }
    }

    public void validateUser (Context context) {
        String token = context.cookie("codeDNA");
        if (token == null || jwtService.validateJWT(token).isEmpty()) {
            throw new ForbiddenResponse("Please login");
        }
        context.status(200).result("User validated");
    }
}
