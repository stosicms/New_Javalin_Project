package book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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