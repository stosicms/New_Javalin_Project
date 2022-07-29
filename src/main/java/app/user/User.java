package app.user;

public class User {
    public String id;
    public String username;
    public String salt;
    public String password;
    public String email;

    public User() {

    }

    public User(String username, String salt, String password, String email) {
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
