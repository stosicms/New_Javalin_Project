package app.book;

import io.javalin.Javalin;
import io.javalin.http.Context;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class BookControllerTest extends TestCase {


    public void testGetAllBooks() {

        Context context = mock(Context.class);
        BookController.getAllBooks(context);
    }

    public void testGetOneBook() {

    }
}