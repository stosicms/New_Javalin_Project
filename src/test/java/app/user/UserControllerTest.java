package app.user;

import app.helpers.JwtHelper;
import com.google.gson.Gson;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    private final Context ctx = mock(Context.class, Mockito.RETURNS_DEEP_STUBS);
    UserController userController;
    JwtHelper jwtHelper;
    UserDao userDao = new UserDao();

    @Test
    void shouldLogIn() {

        JwtHelper jwtHelper = mock(JwtHelper.class);
        UserController userController = new UserController(jwtHelper, new Gson(), userDao);

        Map<String, String> model = new HashMap<>();
        model.put("response", "token");
        when(jwtHelper.generateToken(("perwendel"))).thenReturn(model);
        when(ctx.body()).thenReturn("""
                {
                        "username": "perwendel",
                        "password": "123456789"
                }""");

        userController.logIn(ctx);
        verify(ctx).json(model);

    }

    @Test
    void shouldFailToLogIn() {
        JwtHelper jwtHelper = mock(JwtHelper.class);
        UserController userController = new UserController(jwtHelper, new Gson(), userDao);

        Map<String, String> model = new HashMap<>();
        model.put("response", "token");
        when(jwtHelper.generateToken(("nonExistentUser"))).thenReturn(model);
        when(ctx.body()).thenReturn("""
                {
                        "username": "nonExistentUser",
                        "password": "123456789"
                }""");

        userController.logIn(ctx);
        verify(ctx).status(400);

    }
}
