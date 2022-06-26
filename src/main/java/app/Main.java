package app;

import app.book.BookController;
import app.user.UserController;
import app.util.Path;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(3000);

        app.get(Path.Web.BOOKS, BookController::getAllBooks);
        app.get(Path.Web.ONE_BOOK, BookController::getOneBook);
        app.get(Path.Web.USERNAMES, UserController::getAllUsers);
        app.get(Path.Web.USERNAME, UserController::getOneUser);
        app.post(Path.Web.ADD_BOOK, BookController::addBook);
    }
}
