import app.book.BookController;
import app.user.UserController;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        BookController bookController = new BookController();
        UserController userController = new UserController();



        Javalin app = Javalin.create().start(7000);

        app.get("/books", bookController::fetchAllBooks);
        app.get("/book/{isbn}", bookController::fetchOneBook);
        app.post("/book", bookController::saveBook);
        app.post("/books", bookController::saveBooks);
        app.post("/login", userController::logIn);
        app.post("/signup", userController::signUp);

    }
}
