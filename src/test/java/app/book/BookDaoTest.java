package app.book;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookDaoTest {
    BookDao bookDao = new BookDao();

    @Test
    public void testGetBookByIsbn () {
        assertEquals(12, bookDao.getAllBooks().size());
        assertEquals("9789583001215", bookDao.getBookByIsbn("9789583001215").isbn);
        assertEquals("Moby Dick", bookDao.getBookByIsbn("9789583001215").title);
        assertEquals("Herman Melville", bookDao.getBookByIsbn("9789583001215").author);
    }
}
