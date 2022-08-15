package app.book;

import app.book.repository.BookRepository;
import app.user.UserRepository;
import com.google.gson.Gson;
import io.javalin.http.Context;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class BookControllerTest {

    private final Context ctx = mock(Context.class);
    BookDao bookDao = new BookDao();
    Gson gson = new Gson();
    BookRepository bookRepository = mock(BookRepository.class);
    UserRepository userRepository = mock(UserRepository.class);
    BookController bookController = new BookController(bookDao, new Gson(), bookRepository, userRepository);

    @Test
    void shouldFetchAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        Book book = new Book("Test Title", "Test Author", "isbn");
        books.add(book);
        when(bookRepository.getAll()).thenReturn(books);
        bookController.fetchAllBooks(ctx);
        Map<String, Object> model = new HashMap<>();
        model.put("result", books);
        verify(ctx).json(model);
        System.out.println(model);
    }

    @Test
    void shouldFetchOneBook() throws SQLException {
        when(ctx.pathParam("isbn")).thenReturn("9789583001215");
        Book book = new Book("Moby Dick", "Herman Melville", "9789583001215");
        when(bookRepository.getByIsbn("9789583001215")).thenReturn(book);
        bookController.fetchOneBook(ctx);
        Map<String, Object> model = new HashMap<>();
        model.put("result", book);
        verify(ctx).json(model);
    }

    @Test
    void shouldSaveBook() throws Exception {
        when(ctx.body()).thenReturn("{\n" +
                "        \"isbn\": \"9781591940200\",\n" +
                "        \"title\": \"The adventures of 2 Huckleberry Finn\",\n" +
                "        \"author\": \"Mark Twain\"\n" +
                "    }");

        bookController.saveBook(ctx);
        verify(ctx).status(201);
    }
}