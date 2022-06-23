package app;

import app.book.BookController;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);

        app.get("/books", BookController::fetchAllBooks);
        app.get("/book/{isbn}", BookController::fetchOneBook);
    }


}
