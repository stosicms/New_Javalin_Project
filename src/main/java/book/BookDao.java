package book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookDao {
    private final List<Book> books = new ArrayList<>();

    public BookDao() {
        books.add(new Book("Alice In Wonderland", "Lewis Carrol", "9780439291491"));
        books.add(new Book("Moby Dick", "Herman Melville", "9789583001215"));
        books.add(new Book("A Christmas Carol", "Charles Dickens", "9780141324524"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "9781936594290"));
        books.add(new Book("The Fellowship of The Ring", "J. R. R. Tolkien", "0007171978"));
        books.add(new Book("Harry Potter", "J. K. Rowling", "0747532699"));
        books.add(new Book("War and Peace", "Leo Tolstoy", "9780060798871"));
        books.add(new Book("Don Quixote", "Miguel Cervantes", "9789626345221"));
        books.add(new Book("Ulysses", "James Joyce", "9780394703800"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));
        books.add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", "9780060531041"));
        books.add(new Book("The adventures of Huckleberry Finn", "Mark Twain", "9781591940296"));
    }


    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookByIsbn(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public boolean deleteBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    public boolean addBook(Book book){
        Book bookInDb = getBookByIsbn(book.getIsbn());
        if(bookInDb == null){
            return books.add(book);
        }else{
            return false;
        }
    };

    public boolean addBooks(List<Book> book){
        return books.addAll(book);
    };


    public Book GetRandomBook() {
        return books.get(new Random().nextInt(books.size()));
    }


}
