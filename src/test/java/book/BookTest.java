package book;

import app.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    private Book book;
    String expectedTitle = "Moby Dick";
    String expectedIsbn = "9789583001215";
    String expectedAuthor = "Herman Melville";


    @BeforeEach
    void setUp() {
        book = new Book("Moby Dick", "Herman Melville", "9789583001215");
    }

    @Test
    void checkIfIsbnIsTheSame() {


        String actualIsbn = book.getIsbn();

        assertEquals(expectedIsbn, actualIsbn);
    }

    @Test
    void checkIfTitleIsTheSame() {

        String actualTitle = book.getTitle();

        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    void checkIfAuthorIsTheSame() {

        String actualAuthor = book.getAuthor();

        assertEquals(expectedAuthor, actualAuthor);

    }
}