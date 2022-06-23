package app.book;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class BookController {

    static BookDao bookDao = new BookDao();

    public static void fetchAllBooks(Context ctx) {

        Map<String, Object> model = new HashMap<>();
        model.put("books", bookDao.getAllBooks());
        ctx.json(model);
    }

    public static void fetchOneBook (Context context) {
        Map<String, Object> model = new HashMap<>();
        String isbn = context.pathParam("isbn");
        model.put("app/book", bookDao.getBookByIsbn(isbn));
        context.json(model);
    }
}
