package app.book;

public class Book {
    public final String isbn;
    public final String title;
    public final String author;

    public Book(String title, String author, String isbn) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
}
