package app.book.repository;

import app.config.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookRepository {
    Connection connection;

    public BookRepository(Connection connection){
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
}
