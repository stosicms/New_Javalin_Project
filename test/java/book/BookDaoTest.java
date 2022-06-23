package book;

import app.book.Book;
import app.book.BookDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookDaoTest {

    private final BookDao bookDao = new BookDao();

    @Test
    public void getBookByIsbnTest() {
        String isbn = "9789583001215";
        Book bookIsbn = bookDao.getBookByIsbn(isbn);

        assertEquals(bookIsbn.getIsbn(), isbn);

    }

    @Test
    public void getAllBooksTest() {
        List<Book> books = bookDao.getAllBooks();

        Assertions.assertThat(books.size()).isGreaterThan(0);
    }

}