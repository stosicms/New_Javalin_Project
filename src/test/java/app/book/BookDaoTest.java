package app.book;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookDaoTest {
    BookDao bookDao = new BookDao();

    @Test
    public void testGetBookByIsbn () {
        assertEquals(bookDao.getAllBooks().size(), 12);
        assertEquals(bookDao.getBookByIsbn("9789583001215"), "9789583001215");
    }
}
