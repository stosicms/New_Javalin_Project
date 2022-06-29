package app.user;

import app.book.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao = new UserDao();

    @Test
    void shouldGetUserByUsername() {
        String username = "perwendel";
        String salt = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";
        String password = "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO";
        User userUsername = userDao.getUserByUsername(username);

        assertEquals(username, userUsername.username);
        assertEquals(salt, userUsername.salt);
        assertEquals(password, userUsername.password);
    }

    @Test
    void shouldGetNullByInvalidUsername() {
        String invalidUsername = "blahblah";
        User userByUsername = userDao.getUserByUsername(invalidUsername);

        assertNull(userByUsername);
    }

}