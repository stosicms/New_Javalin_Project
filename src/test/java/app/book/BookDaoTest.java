package app.book;

import org.junit.Test;

import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao = new BookDao();

    @Test
    public void testGetAllBooks () {

        assertEquals(12, bookDao.getAllBooks().size());
        assertEquals("9789583001215", bookDao.getAllBooks().stream().findFirst().get().isbn);

    }

    @Test
    public void testGetBookByIsbn () {

        assertNull(bookDao.getBookByIsbn("wrong isbn"));

        String isbn1 = "9789583001215";
        assertEquals("9789583001215", bookDao.getBookByIsbn(isbn1).isbn);
        assertEquals("Moby Dick", bookDao.getBookByIsbn(isbn1).title);
        assertEquals("Herman Melville", bookDao.getBookByIsbn(isbn1).author);

        String isbn2 = "9780141324524";
        assertEquals("9780141324524", bookDao.getBookByIsbn(isbn2).isbn);
        assertEquals("A Christmas Carol", bookDao.getBookByIsbn(isbn2).title);
        assertEquals("Charles Dickens", bookDao.getBookByIsbn(isbn2).author);

        String isbn3 = "9781936594290";
        assertEquals("9781936594290", bookDao.getBookByIsbn(isbn3).isbn);
        assertEquals("Pride and Prejudice", bookDao.getBookByIsbn(isbn3).title);
        assertEquals("Jane Austen", bookDao.getBookByIsbn(isbn3).author);

        String isbn4 = "0007171978";
        assertEquals("0007171978", bookDao.getBookByIsbn(isbn4).isbn);
        assertEquals("The Fellowship of The Ring", bookDao.getBookByIsbn(isbn4).title);
        assertEquals("J. R. R. Tolkien", bookDao.getBookByIsbn(isbn4).author);

        String isbn5 = "0747532699";
        assertEquals("0747532699", bookDao.getBookByIsbn(isbn5).isbn);
        assertEquals("Harry Potter", bookDao.getBookByIsbn(isbn5).title);
        assertEquals("J. K. Rowling", bookDao.getBookByIsbn(isbn5).author);

        String isbn6 = "9780060798871";
        assertEquals("9780060798871", bookDao.getBookByIsbn(isbn6).isbn);
        assertEquals("War and Peace", bookDao.getBookByIsbn(isbn6).title);
        assertEquals("Leo Tolstoy", bookDao.getBookByIsbn(isbn6).author);

        String isbn7 = "9789626345221";
        assertEquals("9789626345221", bookDao.getBookByIsbn(isbn7).isbn);
        assertEquals("Don Quixote", bookDao.getBookByIsbn(isbn7).title);
        assertEquals("Miguel Cervantes", bookDao.getBookByIsbn(isbn7).author);

        String isbn8 = "9780394703800";
        assertEquals("9780394703800", bookDao.getBookByIsbn(isbn8).isbn);
        assertEquals("Ulysses", bookDao.getBookByIsbn(isbn8).title);
        assertEquals("James Joyce", bookDao.getBookByIsbn(isbn8).author);

        String isbn9 = "9780743273565";
        assertEquals("9780743273565", bookDao.getBookByIsbn(isbn9).isbn);
        assertEquals("The Great Gatsby", bookDao.getBookByIsbn(isbn9).title);
        assertEquals("F. Scott Fitzgerald", bookDao.getBookByIsbn(isbn9).author);

        String isbn10 = "9780060531041";
        assertEquals("9780060531041", bookDao.getBookByIsbn(isbn10).isbn);
        assertEquals("One Hundred Years of Solitude", bookDao.getBookByIsbn(isbn10).title);
        assertEquals("Gabriel Garcia Marquez", bookDao.getBookByIsbn(isbn10).author);

        String isbn11 = "9781591940296";
        assertEquals("9781591940296", bookDao.getBookByIsbn(isbn11).isbn);
        assertEquals("The adventures of Huckleberry Finn", bookDao.getBookByIsbn(isbn11).title);
        assertEquals("Mark Twain", bookDao.getBookByIsbn(isbn11).author);

        String isbn12 = "9780439291491";
        assertEquals("9780439291491", bookDao.getBookByIsbn(isbn12).isbn);
        assertEquals("Alice In Wonderland", bookDao.getBookByIsbn(isbn12).title);
        assertEquals("Lewis Carrol", bookDao.getBookByIsbn(isbn12).author);
    }
    @Test
    public void testAddBook () {
        String isbn13 = "9788661050930";
        bookDao.addBook("9788661050930", "Ivo Andric", "Na Drini cuprija");

        assertEquals("9788661050930", bookDao.getBookByIsbn(isbn13).isbn);
        assertEquals("Ivo Andric", bookDao.getBookByIsbn(isbn13).author);
        assertEquals("Na Drini cuprija", bookDao.getBookByIsbn(isbn13).title);
    }

    @Test
    public void testAddBooks () {
        List<Book> newList = List.of(
                new Book("The Sicilian", "Mario Puzo", "9780099580799"),
                new Book("Death and the dervish", "Mesa Selimovic", "9788610023633"));
        bookDao.addBooks(newList);

        String isbn14 = "9780099580799";
        assertEquals("9780099580799", bookDao.getBookByIsbn(isbn14).isbn);
        assertEquals("Mario Puzo", bookDao.getBookByIsbn(isbn14).author);
        assertEquals("The Sicilian", bookDao.getBookByIsbn(isbn14).title);

        String isbn15 = "9788610023633";
        assertEquals("9788610023633", bookDao.getBookByIsbn(isbn15).isbn);
        assertEquals("Mesa Selimovic", bookDao.getBookByIsbn(isbn15).author);
        assertEquals("Death and the dervish", bookDao.getBookByIsbn(isbn15).title);
    }
    @Test
    public void testDeleteBookByIsbn () {
        String isbn = "9780439291491";
        bookDao.deleteBookByIsbn(isbn);

        assertNull(bookDao.getBookByIsbn(isbn));
    }
}
