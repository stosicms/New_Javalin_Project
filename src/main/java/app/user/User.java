package app.user;

public class User {
    public String username;
    public String salt;
    public String hashedPassword;

    public User () {}

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public User(String username, String salt, String hashedPassword) {
        this.username = username;
        this.salt = salt;
        this.hashedPassword = hashedPassword;
    }
}
