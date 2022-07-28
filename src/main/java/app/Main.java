package app;

import app.book.BookController;
import app.service.auth.AccessManager;
import app.service.auth.AuthController;
import app.service.auth.Roles;
import app.user.UserController;
import app.util.Path;
import io.javalin.Javalin;

import javax.management.relation.Role;

public class Main {
    public static void main(String[] args) {
        AuthController authController = new AuthController();

        Javalin app = Javalin.create(javalinConfig ->
                javalinConfig.accessManager(new AccessManager()::accessManager)).start(3001);

        app.get(Path.Web.BOOKS, BookController::getAllBooks);
        app.get(Path.Web.ONE_BOOK, BookController::getOneBook);
        app.get(Path.Web.USERNAMES, UserController::getAllUsers);
        app.get(Path.Web.USERNAME, UserController::getOneUser);
        app.post(Path.Web.ADD_BOOK, BookController::addBook);
        app.post(Path.Web.ADD_BOOKS, BookController::addBooks);
        app.delete(Path.Web.DELETE_BOOK, BookController::deleteBook);
        app.post(Path.Web.LOGIN, authController::loginUser);
        app.post(Path.Web.VALIDATE, authController::validateUser);
    }
}
