package app.book;

import org.junit.jupiter.api.Test;

import java.util.List;

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

}