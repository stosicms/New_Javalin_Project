//package app.user;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//class UserDaoTest {
//
//    UserDao userDao = new UserDao();
//
//    @Test
//    void shouldGetUserByUsername() {
//        String username = "perwendel";
//        String salt = "$2a$10$ItiCBD2OlV6mUPXwSjjxIO";
//        String password = "$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi";
//        User userUsername = userDao.getUserByUsername(username);
//
//        assertEquals(username, userUsername.username);
//        assertEquals(salt, userUsername.salt);
//        assertEquals(password, userUsername.password);
//    }
//
//    @Test
//    void shouldGetNullByInvalidUsername() {
//        String invalidUsername = "blahblah";
//        User userByUsername = userDao.getUserByUsername(invalidUsername);
//
//        assertNull(userByUsername);
//    }
//
//}