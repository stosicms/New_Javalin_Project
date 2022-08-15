//package app.service;
//
//import app.book.BookController;
//import app.book.BookDao;
//import app.helpers.AuthentificationHandler;
//import app.helpers.ErrorHandler;
//import app.helpers.JwtHelper;
//import com.google.gson.Gson;
//import io.javalin.Javalin;
//import io.javalin.http.HttpResponseException;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class ItTest {
//    static Javalin app = Javalin.create();
//    HttpClient client = HttpClient.newHttpClient();
//    BookDao bookDao;
//    BookController bookController;
//    @BeforeAll
//    static void startJavalin() {
//        BookDao bookDao = new BookDao();
//        BookController bookController = new BookController(bookDao, new Gson());
//        ErrorHandler errorHandler = new ErrorHandler();
//        JwtHelper jwtHelper = new JwtHelper();
//        UserDao userDao = new UserDao();
//
//        AuthentificationHandler authentificationHandler = new AuthentificationHandler(jwtHelper, userDao);
//
//        app.start(7000);
//
//        app.exception(HttpResponseException.class, errorHandler::httpError);
//        app.before("/protected/*", authentificationHandler::validateUser);
//        app.get("/protected/books", bookController::fetchAllBooks);
//        app.get("/protected/book/{isbn}", bookController::fetchOneBook);
//        app.post("/protected/book", bookController::saveBook);
//        app.post("/protected/books", bookController::saveBooks);
////        app.post("/login", userController::logIn);
////        app.post("/signup", userController::signUp);
//
//        System.out.println("Startovao sam javalin");
//    }
//
//    @AfterAll
//    static void stopJavalin() {
//        app.stop();
//        System.out.println("Stopirao sam javalin");
//
//    }
//
//    @Test
//    public void saveBookTest() throws URISyntaxException, IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("http://localhost:7000/books"))
//                .GET()
//                .build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//
//        assertTrue(true);
//
//    }
//}
