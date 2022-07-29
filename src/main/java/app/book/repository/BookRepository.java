package app.book.repository;

import app.book.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    Connection connection;


    public BookRepository(Connection connection) {
        this.connection = connection;

    }

    public void dbPing() throws SQLException {
        String SQL = "SELECT 1";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            System.out.println(rs);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void saveOne(Book book) throws SQLException {
        String sql = """
                INSERT INTO "Book"(isbn, title, author) VALUES (?, ?, ?);
                """;
        try {
            PreparedStatement pst = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, book.getIsbn());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getAuthor());

            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            rs.next();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Book getBookInstance(ResultSet res) {
        Book book = null;
        try {
            book = new Book(res.getString("title"), res.getString("author"), res.getString("isbn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getAllBooks() throws SQLException {
        {
            List<Book> books = new ArrayList<>();

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("""
                    SELECT * FROM "Book";
                    """);

            while (res.next()) {
                books.add(getBookInstance(res));
            }
            return books;
        }
    }




}
