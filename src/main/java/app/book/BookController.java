package app.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;


public class BookController {
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
    public static void addBook (Context context) throws JsonProcessingException {
        String request = context.body();
        Book newBook = new ObjectMapper().readValue(request, Book.class);
        bookDao.addBook(newBook.isbn, newBook.author, newBook.title);
        context.status(201);
    }
}
