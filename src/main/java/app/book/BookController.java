package app.book;

import app.book.repository.BookRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.javalin.http.Context;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookController {
    BookRepository bookRepository;
    Gson gson;
    BookDao bookDao;
    public BookController(BookDao bookDao, Gson gson, BookRepository bookRepository) {
        this.bookDao = bookDao;
        this.gson = gson;
        this.bookRepository = bookRepository;
    }


    private <T> List<T> getListFromJson(String jsonArray, Class<T> clazz) {
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return new Gson().fromJson(jsonArray, typeOfT);
    }

    public void fetchAllBooks(Context ctx) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        model.put("result", bookRepository.getAllBooks());
        ctx.json(model);
    }

    public void fetchOneBook(Context context) {
        Map<String, Object> model = new HashMap<>();
        String isbn = context.pathParam("isbn");
        model.put("result", bookDao.getBookByIsbn(isbn));
        context.json(model);
    }

    public void saveBook(Context ctx) throws Exception {
        String requestBodyAsString = ctx.body();
        Book requestBodyAsJson = gson.fromJson(requestBodyAsString, Book.class);
        Book newBook = new Book(requestBodyAsJson.title, requestBodyAsJson.author, requestBodyAsJson.isbn);
        bookRepository.saveOne(newBook);
        ctx.status(201);
    }

    public void saveBooks(Context ctx) {
        String requestBodyAsString = ctx.body();
        List<Book> requestBodyAsJson = getListFromJson(requestBodyAsString, Book.class);
        List<Book> newBooks = requestBodyAsJson.stream().map(bookData -> new Book(bookData.title, bookData.author, bookData.isbn)).toList();
        bookDao.saveMany(newBooks);
        ctx.status(201);
    }

}
