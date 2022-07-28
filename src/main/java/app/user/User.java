package app.user;

public class User {
    public String username;
    public String salt;
    public String password;

    public User () {}

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String salt, String password) {
        this.username = username;
        this.salt = salt;
        this.password = password;
    }
}
