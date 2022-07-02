import app.book.BookController;
import app.user.UserController;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

//        BookController bookController = new BookController();
//        UserController userController = new UserController();



        Javalin app = Javalin.create().start(7000);

        app.get("/books", BookController::fetchAllBooks);
        app.get("/book/{isbn}", BookController::fetchOneBook);
        app.post("/book", BookController::saveBook);
        app.post("/books", BookController::saveBooks);
//        app.post("/login", BookController::logIn);
//        app.post("/signup", BookController::signUp);

    }
}
