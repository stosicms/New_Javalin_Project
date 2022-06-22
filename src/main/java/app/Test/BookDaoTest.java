package app.Test;

import app.book.BookDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    BookDao bookDao = new BookDao();

    @Test
    public void testBookDao(){
        String autor = bookDao.getAllBooks().get(4).author;

        assertEquals(12, bookDao.getAllBooks().size());
        assertEquals(bookDao.getAllBooks().get(1).isbn, "A Christmas Carol" );
        assertEquals(bookDao.getAllBooks().get(5).title, "Leo Tolstoy" );
        assertEquals(bookDao.getAllBooks().get(8).author,"9780743273565" );
        assertTrue(bookDao.getAllBooks().get(0).author.compareTo("9789583001215"));
        assertEquals(bookDao.getAllBooks().get(7).isbn, "Ulysses");

        
    }

    private void assertTrue(int compareTo) {
    }


}