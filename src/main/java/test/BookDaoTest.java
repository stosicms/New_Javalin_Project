package test;

import book.Book;
import book.BookDao;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class BookDaoTest {
    BookDao bookDao = new BookDao();

    @Test

    public void testGetAllBook() {
        assertEquals(12, bookDao.getAllBooks().size());
    }

    @Test
    public void testDeleteOneBook() {
        String isbn = "9781936594290";
        bookDao.deleteBookByIsbn(isbn);
        assertEquals(11, bookDao.getAllBooks().size());
        assertNull(bookDao.getBookByIsbn(isbn));
    }


    @Test
    public void testGetBookByIsbn() {
        String isbn0 = "9780439291491";
        assertEquals("9780439291491", bookDao.getBookByIsbn(isbn0).isbn);

        String isbn1 = "9789583001215";
        assertEquals("9780439291491", bookDao.getBookByIsbn(isbn1).isbn);

        String isbn2 = "9780141324524";
        assertEquals("9780439291491", bookDao.getBookByIsbn(isbn2).isbn);

        String isbn3 = "9781936594290";
        assertEquals("9780439291491", bookDao.getBookByIsbn(isbn3).isbn);
    }

    @Test
    public void testAddBook() {
        String isbn = "10101010101010";
        String author = "Ernest Hemingway";
        String title = "The Old Man and the Sea";
        Book newBook = new Book(title, author, isbn);
        bookDao.addBook(newBook);

        assertEquals(13, bookDao.getAllBooks().size());
        assertEquals(isbn, bookDao.getBookByIsbn(isbn).isbn);
        assertEquals(author, bookDao.getBookByIsbn(isbn).author);
        assertEquals(title, bookDao.getBookByIsbn(isbn).title);

    }
}
