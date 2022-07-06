package app.book;

import io.javalin.http.Context;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static app.book.BookController.bookDao;
import static org.mockito.Mockito.*;

class BookControllerTest {

    private final Context ctx = mock(Context.class);

    BookDao bookDao =  BookDao.getInstance();
    @Test
    void shouldFetchAllBooks() {
        BookController.fetchAllBooks(ctx);
        Map<String, Object> model = new HashMap<>();
        model.put("result", bookDao.getAllBooks());
        verify(ctx).json(model);
        System.out.println(model);
    }

    @Test
    void shouldFetchOneBook() {
        when(ctx.pathParam("isbn")).thenReturn("9789583001215");
        BookController.fetchOneBook(ctx);
        Map<String, Object> model = new HashMap<>();
        model.put("result", bookDao.getBookByIsbn("9789583001215"));
        verify(ctx).json(model);
    }

    @Test
    void shouldSaveBook() throws Exception {
        when(ctx.body()).thenReturn("{\n" +
                "        \"isbn\": \"9781591940200\",\n" +
                "        \"title\": \"The adventures of 2 Huckleberry Finn\",\n" +
                "        \"author\": \"Mark Twain\"\n" +
                "    }");

        BookController.saveBook(ctx);
        verify(ctx).status(201);
    }
}