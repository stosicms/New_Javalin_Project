package app.book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.javalin.http.Context;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookController {

    static BookDao bookDao = new BookDao();
    static Gson gson = new Gson();

    private static <T> List<T> getList(String jsonArray, Class<T> clazz) {
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return new Gson().fromJson(jsonArray, typeOfT);
    }

    public static void fetchAllBooks(Context ctx) {

        Map<String, Object> model = new HashMap<>();
        model.put("books", bookDao.getAllBooks());
        ctx.json(model);
    }

    public static void fetchOneBook(Context context) {
        Map<String, Object> model = new HashMap<>();
        String isbn = context.pathParam("isbn");
        model.put("app/book", bookDao.getBookByIsbn(isbn));
        context.json(model);
    }

    public static void saveBook(Context ctx) {
        String requestBodyAsString = ctx.body();
        Book requestBodyAsJson = gson.fromJson(requestBodyAsString, Book.class);
        bookDao.saveOne(new Book(requestBodyAsJson.title, requestBodyAsJson.author, requestBodyAsJson.isbn));
        ctx.status(201);
    }

    public static void saveBooks(Context ctx) {
        String requestBodyAsString = ctx.body();
        List<Book> requestBodyAsJson = getList(requestBodyAsString, Book.class);
        List<Book> newBooks = requestBodyAsJson.stream().map(bookData -> new Book(bookData.title, bookData.author, bookData.isbn)).toList();
        bookDao.saveMany(newBooks);
        ctx.status(201);
    }


}
