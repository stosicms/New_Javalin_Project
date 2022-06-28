package book;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import io.javalin.http.Context;

public class BookController {

    static BookDao bookDao = new BookDao();

    public static void fetchAllBooks(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("books", bookDao.getAllBooks());
        ctx.json(model);
    }

    public static void fetchOneBook(Context ctx) {
        String isbn = ctx.pathParam("isbn");
        Book book = bookDao.getBookByIsbn(isbn);
        ctx.json(book == null ? "Book Unavailable" : book);
    }

    public static void deleteOneBook(Context ctx) {
        String isbn = ctx.pathParam("isbn");
        ctx.json(bookDao.deleteBookByIsbn(isbn));
    }

    public static void addBook(Context ctx) {
        Gson gson = new Gson();
        String body = ctx.body();
        final Book book = gson.fromJson(body, Book.class);
        boolean bookAdded = bookDao.addBook(book);
        ctx.json(bookAdded ? "Successful" : "Unsuccessful");
    };

    public static void addBooks(Context ctx) {
//        Gson gson = new Gson();
//        String body = ctx.body();
//

    };




}
