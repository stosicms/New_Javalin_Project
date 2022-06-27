package app.book;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

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
    public static void addBooks (Context context) {

    }
}
