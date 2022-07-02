package app.book;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class
BookDaoTest {

    private final BookDao bookDao = new BookDao();

    @Test
    public void shouldGetBookByIsbn() {
        String isbn = "9789583001215";
        String title = "Moby Dick";
        String author = "Herman Melville";
        Book bookIsbn = bookDao.getBookByIsbn(isbn);

        assertEquals(isbn, bookIsbn.getIsbn());
        assertEquals(author, bookIsbn.getAuthor());
        assertEquals(title, bookIsbn.getTitle());

    }

    @Test
    void shouldGetNullByInvalidIsbn() {
        String invalidIsbn = "1";
        Book bookIsbn = bookDao.getBookByIsbn(invalidIsbn);

        assertNull(bookIsbn);
    }

    @Test
    void shouldGetBooksCount() {
        int booksCount = 12;
        List<Book> books = bookDao.getAllBooks();

        assertEquals(booksCount, books.size());
    }

    @Test
    void shouldGetRandomBook() {
        Book randomBook = bookDao.getRandomBook();
        int validIsbnLength = 13;

        assertNotNull(randomBook);
        assertEquals(validIsbnLength, randomBook.getIsbn().length());
    }

    @Test
    void shouldSaveBook() {
        String isbn = "9000583001215";
        String title = "Test Title";
        String author = "Test Author";
        bookDao.saveOne(new Book(title, author, isbn));
        Book savedBook = bookDao.getBookByIsbn(isbn);

        assertNotNull(savedBook);
        assertEquals(isbn, savedBook.isbn);
        assertEquals(title, savedBook.title);
        assertEquals(author, savedBook.author);

    }

    @Test
    public void shouldSaveBookCon() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        String isbn = "9789583001215";
        String title = "Moby Dick";
        String author = "Herman Melville";
        Book book1 = new Book(isbn, title, author);
        bookDao.saveOne(book1);

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            bookDao.saveOne(book1);
        });

        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            bookDao.saveOne(book1);
        });

        f1.thenCombine(f2, (v1, v2) -> {
            System.out.println("Zavrsili smo cuvanje");
            return null;
        }).get();

        List<Book> books = bookDao.getAllBooks();

        Gson gson = new Gson();
        System.out.println(gson.toJson(books));

        assertEquals(isbn, book1.getIsbn());
        assertEquals(author, book1.getAuthor());
        assertEquals(title, book1.getTitle());

    }

}