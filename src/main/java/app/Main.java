package app;

import app.book.BookController;
import app.book.BookDao;
import app.helpers.AuthentificationHandler;
import app.helpers.ErrorHandler;
import app.helpers.JwtHelper;
import app.user.UserController;
import app.user.UserDao;
import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.http.HttpResponseException;

public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        Gson gson = new Gson();
        BookDao bookDao = new BookDao();
        BookController bookController = new BookController(bookDao, gson);
        JwtHelper jwtHelper = new JwtHelper();
        AuthentificationHandler authentificationHandler = new AuthentificationHandler(jwtHelper, userDao);
        UserController userController = new UserController(jwtHelper, gson, userDao);
        ErrorHandler errorHandler = new ErrorHandler();
        //HttpError httpError =

        Javalin app = Javalin.create().start(7000);

        app.exception(HttpResponseException.class, errorHandler::httpError);
        app.before("/protected/*", authentificationHandler::validateUser);
        app.get("/protected/books", bookController::fetchAllBooks);
        app.get("/protected/book/{isbn}", bookController::fetchOneBook);
        app.post("/protected/book", bookController::saveBook);
        app.post("/protected/books", bookController::saveBooks);
        app.post("/login", userController::logIn);
        app.post("/signup", userController::signUp);




    }
}
