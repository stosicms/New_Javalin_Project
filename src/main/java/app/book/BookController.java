package app.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BookController {
    public BookController() {}

    static BookDao bookDao = new BookDao();


    public static void getAllBooks (Context context) {
        List<Book> result = bookDao.getAllBooks();
        if (result.size() == 0){
            throw new NotFoundResponse("There is no books");
        }
        context.json(result);
    }

    public static void getOneBook (Context context) {
        String isbn = context.pathParam("isbn");
        Book result = bookDao.getBookByIsbn(isbn);
        if (result == null){
            throw new NotFoundResponse("Wrong book");
        }
        context.json(result);
    }
    public static void addBook (Context context) {
        Book newBook = context.bodyAsClass(Book.class);
        bookDao.addBook(newBook);
        context.status(201);
    }
    public static void addBooks (Context context) throws JsonProcessingException {
        var bookStr = context.body();
        final ObjectMapper objectMapper = new ObjectMapper();
        Book[] bookAr = objectMapper.readValue(bookStr, Book[].class);
        List<Book> newBooks = new ArrayList<>(Arrays.asList(bookAr));
        bookDao.addBooks(newBooks);
    }
    public static void deleteBook (Context context) {
        var isbn = context.pathParam("isbn");
        bookDao.deleteBookByIsbn(isbn);
    }
}
