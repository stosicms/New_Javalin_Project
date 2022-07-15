package app.config;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private final String url = "jdbc:postgresql://localhost:5432/booksdata";
    private final String user = "postgres";
    private final String password = "shadym1988";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void runMigrationsUp() {
        Flyway flyway = Flyway.configure().cleanDisabled(false).dataSource(url, user, password).load();
        flyway.clean();
        flyway.migrate();
    }
}
