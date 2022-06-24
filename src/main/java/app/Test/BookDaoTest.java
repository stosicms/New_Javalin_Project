package app.Test;

import app.book.BookDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    BookDao bookDao = new BookDao();

    @Test
    public void testGetAllBooks() {

        assertEquals(12, bookDao.getAllBooks().size());

        assertEquals("9789583001215", bookDao.getAllBooks().get(0).isbn);
        assertEquals("Herman Melville", bookDao.getAllBooks().get(0).author);
        assertEquals("Moby Dick", bookDao.getAllBooks().get(0).title);

        assertEquals("A Christmas Carol", bookDao.getAllBooks().get(1).title);
        assertEquals("Charles Dickens", bookDao.getAllBooks().get(1).author);
        assertEquals("9780141324524", bookDao.getAllBooks().get(1).isbn);

        assertEquals("Pride and Prejudice", bookDao.getAllBooks().get(2).title);
        assertEquals("Jane Austen", bookDao.getAllBooks().get(2).author);
        assertEquals("9781936594290", bookDao.getAllBooks().get(2).isbn);

        assertEquals("The Fellowship of The Ring", bookDao.getAllBooks().get(3).title);
        assertEquals("J. R. R. Tolkien", bookDao.getAllBooks().get(3).author);
        assertEquals("0007171978", bookDao.getAllBooks().get(3).isbn);

        assertEquals("Harry Potter", bookDao.getAllBooks().get(4).title);
        assertEquals("J. K. Rowling", bookDao.getAllBooks().get(4).author);
        assertEquals("0747532699", bookDao.getAllBooks().get(4).isbn);

        assertEquals("War and Peace", bookDao.getAllBooks().get(5).title);
        assertEquals("Leo Tolstoy", bookDao.getAllBooks().get(5).author);
        assertEquals("9780060798871", bookDao.getAllBooks().get(5).isbn);

        assertEquals("Don Quixote", bookDao.getAllBooks().get(6).title);
        assertEquals("Miguel Cervantes", bookDao.getAllBooks().get(6).author);
        assertEquals("9789626345221", bookDao.getAllBooks().get(6).isbn);

        assertEquals("Ulysses", bookDao.getAllBooks().get(7).title);
        assertEquals("James Joyce", bookDao.getAllBooks().get(7).author);
        assertEquals("9780394703800", bookDao.getAllBooks().get(7).isbn);

        assertEquals("The Great Gatsby", bookDao.getAllBooks().get(8).title);
        assertEquals("F. Scott Fitzgerald", bookDao.getAllBooks().get(8).author);
        assertEquals("9780743273565", bookDao.getAllBooks().get(8).isbn);

        assertEquals("One Hundred Years of Solitude", bookDao.getAllBooks().get(9).title);
        assertEquals("Gabriel Garcia Marquez", bookDao.getAllBooks().get(9).author);
        assertEquals("9780060531041", bookDao.getAllBooks().get(9).isbn);

        assertEquals("The adventures of Huckleberry Finn", bookDao.getAllBooks().get(10).title);
        assertEquals("Mark Twain", bookDao.getAllBooks().get(10).author);
        assertEquals("9781591940296", bookDao.getAllBooks().get(10).isbn);

        assertEquals("Alice In Wonderland", bookDao.getAllBooks().get(11).title);
        assertEquals("Lewis Carrol", bookDao.getAllBooks().get(11).author);
        assertEquals("9780439291491", bookDao.getAllBooks().get(11).isbn);

    }
        @Test
        public void getBookByIsbnTest (){


            assertEquals(13, bookDao.getBookByIsbn("9789583001215").getIsbn().length());
            assertEquals("9789583001215", bookDao.getBookByIsbn("9789583001215").isbn);
            assertEquals("Moby Dick", bookDao.getBookByIsbn("9789583001215").title);
            assertEquals(9, bookDao.getBookByIsbn("9789583001215").title.length());
            assertEquals(15, bookDao.getBookByIsbn("9789583001215").author.length());
            assertEquals("Herman Melville", bookDao.getBookByIsbn("9789583001215").author);

assertEquals(13, bookDao.getBookByIsbn("9780141324524").getIsbn().length());
            assertEquals("9780141324524", bookDao.getBookByIsbn("9780141324524").isbn);
            assertEquals("A Christmas Carol", bookDao.getBookByIsbn("9780141324524").title);
            assertEquals(17, bookDao.getBookByIsbn("9780141324524").title.length());
            assertEquals(15, bookDao.getBookByIsbn("9780141324524").author.length());
            assertEquals("Charles Dickens", bookDao.getBookByIsbn("9780141324524").author);

assertEquals(13, bookDao.getBookByIsbn("9781936594290").getIsbn().length());
            assertEquals("9781936594290", bookDao.getBookByIsbn("9781936594290").isbn);
            assertEquals("Pride and Prejudice", bookDao.getBookByIsbn("9781936594290").title);
            assertEquals(19, bookDao.getBookByIsbn("9781936594290").title.length());
            assertEquals(11, bookDao.getBookByIsbn("9781936594290").author.length());
            assertEquals("Jane Austen", bookDao.getBookByIsbn("9781936594290").author);

assertEquals(10, bookDao.getBookByIsbn("0007171978").getIsbn().length());
            assertEquals("0007171978", bookDao.getBookByIsbn("0007171978").isbn);
            assertEquals("The Fellowship of The Ring", bookDao.getBookByIsbn("0007171978").title);
            assertEquals(26, bookDao.getBookByIsbn("0007171978").title.length());
            assertEquals(16, bookDao.getBookByIsbn("0007171978").author.length());
            assertEquals("J. R. R. Tolkien", bookDao.getBookByIsbn("0007171978").author);

           assertEquals(10, bookDao.getBookByIsbn("0747532699").getIsbn().length());
            assertEquals("0747532699", bookDao.getBookByIsbn("0747532699").isbn);
            assertEquals("Harry Potter", bookDao.getBookByIsbn("0747532699").title);
            assertEquals(12, bookDao.getBookByIsbn("0747532699").title.length());
            assertEquals(13, bookDao.getBookByIsbn("0747532699").author.length());
            assertEquals("J. K. Rowling", bookDao.getBookByIsbn("0747532699").author);

           assertEquals(13, bookDao.getBookByIsbn("9780060798871").getIsbn().length());
            assertEquals("9780060798871", bookDao.getBookByIsbn("9780060798871").isbn);
            assertEquals("War and Peace", bookDao.getBookByIsbn("9780060798871").title);
            assertEquals(13, bookDao.getBookByIsbn("9780060798871").title.length());
            assertEquals(11, bookDao.getBookByIsbn("9780060798871").author.length());
            assertEquals("Leo Tolstoy", bookDao.getBookByIsbn("9780060798871").author);

           assertEquals(13, bookDao.getBookByIsbn("9789626345221").getIsbn().length());
            assertEquals("9789626345221", bookDao.getBookByIsbn("9789626345221").isbn);
            assertEquals("Don Quixote", bookDao.getBookByIsbn("9789626345221").title);
            assertEquals(11, bookDao.getBookByIsbn("9789626345221").title.length());
            assertEquals(16, bookDao.getBookByIsbn("9789626345221").author.length());
            assertEquals("Miguel Cervantes", bookDao.getBookByIsbn("9789626345221").author);

           assertEquals(13, bookDao.getBookByIsbn("9780394703800").getIsbn().length());
            assertEquals("9780394703800", bookDao.getBookByIsbn("9780394703800").isbn);
            assertEquals("Ulysses", bookDao.getBookByIsbn("9780394703800").title);
            assertEquals(7, bookDao.getBookByIsbn("9780394703800").title.length());
            assertEquals(11, bookDao.getBookByIsbn("9780394703800").author.length());
            assertEquals("James Joyce", bookDao.getBookByIsbn("9780394703800").author);

assertEquals(13, bookDao.getBookByIsbn("9780743273565").getIsbn().length());
            assertEquals("9780743273565", bookDao.getBookByIsbn("9780743273565").isbn);
            assertEquals("The Great Gatsby", bookDao.getBookByIsbn("9780743273565").title);
            assertEquals(16, bookDao.getBookByIsbn("9780743273565").title.length());
            assertEquals(19, bookDao.getBookByIsbn("9780743273565").author.length());
            assertEquals("F. Scott Fitzgerald", bookDao.getBookByIsbn("9780743273565").author);

assertEquals(13, bookDao.getBookByIsbn("9780060531041").getIsbn().length());
            assertEquals("9780060531041", bookDao.getBookByIsbn("9780060531041").isbn);
            assertEquals("One Hundred Years of Solitude", bookDao.getBookByIsbn("9780060531041").title);
            assertEquals(29, bookDao.getBookByIsbn("9780060531041").title.length());
            assertEquals(22, bookDao.getBookByIsbn("9780060531041").author.length());
            assertEquals("Gabriel Garcia Marquez", bookDao.getBookByIsbn("9780060531041").author);

            assertEquals(13, bookDao.getBookByIsbn("9781591940296").getIsbn().length());
            assertEquals("9781591940296", bookDao.getBookByIsbn("9781591940296").isbn);
            assertEquals("The adventures of Huckleberry Finn", bookDao.getBookByIsbn("9781591940296").title);
            assertEquals(34, bookDao.getBookByIsbn("9781591940296").title.length());
            assertEquals(10, bookDao.getBookByIsbn("9781591940296").author.length());
            assertEquals("Mark Twain", bookDao.getBookByIsbn("9781591940296").author);

            assertEquals(13, bookDao.getBookByIsbn("9780439291491").getIsbn().length());
            assertEquals("9780439291491", bookDao.getBookByIsbn("9780439291491").isbn);
            assertEquals("Alice In Wonderland", bookDao.getBookByIsbn("9780439291491").title);
            assertEquals(19, bookDao.getBookByIsbn("9780439291491").title.length());
            assertEquals(12, bookDao.getBookByIsbn("9780439291491").author.length());
            assertEquals("Lewis Carrol", bookDao.getBookByIsbn("9780439291491").author);






        }





//        assertEquals(12, bookDao.getAllBooks().size());
//        assertEquals(bookDao.getAllBooks().get(1).isbn, "9780141324524");
//        assertEquals(bookDao.getAllBooks().get(5).title, "War and Peace");
//        assertEquals(bookDao.getAllBooks().get(8).author, "F. Scott Fitzgerald");
//        assertEquals(bookDao.getAllBooks().get(7).isbn, "9780394703800");
//        assertNull(bookDao.getBookByIsbn("wrong isbn"));
//        String isbn1 = "9789583001215";
//        assertEquals("9789583001215", bookDao.getBookByIsbn(isbn1).isbn);







}