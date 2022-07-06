package app.book;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class
BookDaoTest {

    private final BookDao bookDao = BookDao.getInstance();

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
        System.out.println(books.size());
//        assertEquals(booksCount, books.size());
    }

    @Test
    void shouldGetRandomBook() {
        Book randomBook = bookDao.getRandomBook();
        int validIsbnLength = 13;

        assertNotNull(randomBook);
        // verovatno neka knjiga nema 13 cifara u isbn, proveriti koja
        assertEquals(validIsbnLength, randomBook.getIsbn().length());
    }

    @Test
    void shouldSaveBook() throws Exception {
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
    public void shouldSaveBookConcurrent()  throws ExecutionException, InterruptedException {

//       ExecutorService executorService = Executors.newCachedThreadPool();

        String isbn = "9789583001999";
        String title = "New Moby Dick";
        String author = "New Herman Melville";
        int numberOfBooksBeforeSaving = bookDao.getAllBooks().size();

        Book book1 = new Book( title, author, isbn);
        Book book2 = new Book( title, author, isbn);

        AtomicBoolean shouldOneFutureOfSavingBooksFail = new AtomicBoolean(false);

        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            try {
                bookDao.saveOne(book1);
            } catch (Exception e) {
                shouldOneFutureOfSavingBooksFail.set(true);
            }
        });

        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            try {
                bookDao.saveOne(book2);
            } catch (Exception e) {
                shouldOneFutureOfSavingBooksFail.set(true);
            }
        });

        f1.thenCombine(f2, (v1, v2) -> {
            return null;
        }).get();

        int numberOfBooksAfterSaving = bookDao.getAllBooks().size();

        assertTrue(shouldOneFutureOfSavingBooksFail.get());
        assertEquals(numberOfBooksAfterSaving, numberOfBooksBeforeSaving + 1 );
        assertEquals(isbn, book1.getIsbn());
        assertEquals(author, book1.getAuthor());
        assertEquals(title, book1.getTitle());

    }

}