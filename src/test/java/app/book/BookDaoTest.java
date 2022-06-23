package app.book;

import app.book.Book;
import app.book.BookDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    private final BookDao bookDao = new BookDao();

    @Test
    public void shouldGetBookByIsbn() {
        String isbn = "9789583001215";
        String title = "Moby Dick";
        String author = "Herman Melville";
        Book bookIsbn = bookDao.getBookByIsbn(isbn);

        assertEquals(bookIsbn.getIsbn(), isbn);
        assertEquals(bookIsbn.getAuthor(), author);
        assertEquals(bookIsbn.getTitle(), title);

    }

    @Test
    void shouldGetNullByWrongIsbn() {
        String wrongIsbn = "1";
        Book bookIsbn = bookDao.getBookByIsbn(wrongIsbn);

        assertNull(bookIsbn);
    }

    @Test
    void shouldGetBooksCount() {
        int booksCount = 12;
        List<Book> books = bookDao.getAllBooks();

        assertEquals(books.size(), booksCount);
    }

    @Test
    void shouldGetRandomBook() {
        Book randomBook = bookDao.getRandomBook();
        int validIsbnLength = 13;

        assertNotNull(randomBook);
        assertEquals(randomBook.getIsbn().length(), validIsbnLength);

    }

}