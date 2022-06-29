package app.user;

public class User {
    public final String username;
    public final String salt;
    public final String password;

    public User(String username, String salt, String password) {
        this.username = username;
        this.salt = salt;
        this.password = password;
    }
}
