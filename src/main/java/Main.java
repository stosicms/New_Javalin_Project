import book.Book;
import book.BookController;
import book.BookDao;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        BookDao bookDao = new BookDao();

        Javalin app = Javalin.create().start(7001);
        app.get("/books", BookController::fetchAllBooks);
        app.get("/books/{isbn}", BookController::fetchOneBook);
        app.delete("/books/{isbn}", BookController::deleteOneBook);
        //app.post("/books", BookController::addBook);
        app.post("/books", BookController::addBooks);
    }
}
