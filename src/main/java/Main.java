import app.book.BookController;
import app.user.UserController;
import app.registration.UserSignUpController;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);

        app.get("/books", BookController::fetchAllBooks);
        app.get("/book/{isbn}", BookController::fetchOneBook);
        app.post("/book", BookController::saveBook);
        app.post("/books", BookController::saveBooks);
        app.post("/login", UserController::logIn);
        app.post("/signup", UserSignUpController::signUp);
        app.get("/users", UserSignUpController::getAllUsers);
    }
}
