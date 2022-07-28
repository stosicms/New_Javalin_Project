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

        app.get(Path.Web.BOOKS, BookController::getAllBooks, Roles.ANYONE);
        app.get(Path.Web.ONE_BOOK, BookController::getOneBook, Roles.ANYONE);
        app.get(Path.Web.USERNAMES, UserController::getAllUsers, Roles.USER);
        app.get(Path.Web.USERNAME, UserController::getOneUser, Roles.USER);
        app.post(Path.Web.ADD_BOOK, BookController::addBook, Roles.USER);
        app.post(Path.Web.ADD_BOOKS, BookController::addBooks, Roles.USER);
        app.delete(Path.Web.DELETE_BOOK, BookController::deleteBook, Roles.USER);
        app.post(Path.Web.LOGIN, authController::loginUser, Roles.ANYONE);
        app.post(Path.Web.VALIDATE, authController::validateUser, Roles.ANYONE);
    }
}