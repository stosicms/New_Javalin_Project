package app.user;

import app.helpers.JwtHelper;
import com.google.gson.Gson;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    private final Context ctx = mock(Context.class, Mockito.RETURNS_DEEP_STUBS);
    UserController userController;
    JwtHelper jwtHelper;
    UserRepository userRepository = mock(UserRepository.class);


    @Test
    void shouldLogIn() throws SQLException {

        JwtHelper jwtHelper = mock(JwtHelper.class);
        UserController userController = new UserController(jwtHelper, new Gson(), userRepository);

        Map<String, String> model = new HashMap<>();
        model.put("response", "token");
        when(jwtHelper.generateToken(("00000000-bead-4e5d-abff-000000000003"))).thenReturn(model);
        when(ctx.body()).thenReturn("""
                {
                        "email": "perwendel@gmail.com",
                        "password": "123456789"
                }""");

        User user = new User("pecko", "$2a$10$ItiCBD2OlV6mUPXwSjjxIO", "$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi", "perwendel@gmail.com");
        user.setId("00000000-bead-4e5d-abff-000000000003");
        when(userRepository.getUserByEmail("perwendel@gmail.com")).thenReturn(user);
        userController.logIn(ctx);
        verify(ctx).json(model);

    }

//    @Test
//    void shouldFailToLogIn() {
//        JwtHelper jwtHelper = mock(JwtHelper.class);
//        UserController userController = new UserController(jwtHelper, new Gson(), userDao);
//
//        Map<String, String> model = new HashMap<>();
//        model.put("response", "token");
//        when(jwtHelper.generateToken(("nonExistentUser"))).thenReturn(model);
//        when(ctx.body()).thenReturn("""
//                {
//                        "username": "nonExistentUser",
//                        "password": "123456789"
//                }""");
//
//        userController.logIn(ctx);
//        verify(ctx).status(400);
//
//    }
}
