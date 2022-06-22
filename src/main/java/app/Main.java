package app;

import app.book.BookController;
import app.book.BookDao;
import app.util.Path;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        BookDao bookDao = new BookDao();

        Javalin app = Javalin.create().start(3000);

        app.get(Path.Web.BOOKS, BookController::getAllBooks);
        app.get(Path.Web.ONE_BOOK, BookController::getOneBook);
    }
}
