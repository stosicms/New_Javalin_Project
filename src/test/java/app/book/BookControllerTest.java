package app.book;

import io.javalin.http.Context;
import junit.framework.TestCase;
import java.util.List;

import static org.mockito.Mockito.*;

public class BookControllerTest extends TestCase {

    public BookDao bookDao = new BookDao();
    Context context = mock(Context.class);

    public void testGetAllBooks() {

        BookController.getAllBooks(context);
        List<Book> model = bookDao.getAllBooks();
        context.json(model);
        verify(context).json(model);
    }

    public void testGetOneBook() {

        when(context.pathParam("isbn")).thenReturn("9780060798871");
        BookController.getOneBook(context);
        Book model = bookDao.getBookByIsbn("9780060798871");
        context.json(model);
        verify(context).json(model);
    }

    public void testAddBook() {
        when(context.queryParam("isbn")).thenReturn("9781591940200");
        BookController.addBook(context);
        verify(context).status(201);
    }
}