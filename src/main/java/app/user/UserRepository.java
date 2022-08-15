package app.user;

import app.user.User;

import java.sql.*;

public class UserRepository {

    Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User saveOne(User user) throws SQLException {
        String sql = """
                INSERT INTO "User"(username, salt, password, email) VALUES (?, ?, ?, ?) RETURNING id;
                """;
        PreparedStatement pst = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, user.username);
        pst.setString(2, user.salt);
        pst.setString(3, user.password);
        pst.setString(4, user.email);

        pst.executeUpdate();
        ResultSet res = pst.getGeneratedKeys();
        if (res.next()) {
            user.setId(res.getString("id"));

        }
        return user;
    }

    public User getUserById(String id) throws SQLException {
        User user = new User();


        PreparedStatement stmt = connection.prepareStatement("""
                    SELECT * FROM "User" WHERE id = uuid(?)
                """);

        stmt.setObject(1, id);
        ResultSet res = stmt.executeQuery();

        if (res.next()) {

            user.setUsername(res.getString("username"));
            user.setSalt(res.getString("salt"));
            user.setPassword(res.getString("password"));
            user.setEmail(res.getString("email"));
            user.setId(res.getString("id"));
        }
        return user;

    }

    public User getUserByEmail(String email) throws SQLException {
        User user = new User();


        PreparedStatement stmt = connection.prepareStatement("""
                    SELECT * FROM "User" WHERE email=?
                """);

        stmt.setObject(1, email);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            user.setUsername(res.getString("username"));
            user.setSalt(res.getString("salt"));
            user.setPassword(res.getString("password"));
            user.setEmail(res.getString("email"));
            user.setId(res.getString("id"));
        }
        return user;

    }

}
