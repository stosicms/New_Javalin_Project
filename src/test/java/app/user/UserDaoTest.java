package app.user;

import junit.framework.TestCase;


public class UserDaoTest extends TestCase {

    UserDao userDao = new UserDao();

    public void testGetUserByUsername() {

        assertNull(userDao.getUserByUsername("Wrong username"));

        String username1 = "perwendel";
        assertEquals("perwendel", userDao.getUserByUsername(username1).username);
        assertEquals("$2a$10$h.dl5J86rGH7I8bD9bZeZe", userDao.getUserByUsername(username1).salt);
        assertEquals("$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO", userDao.getUserByUsername(username1).hashedPassword);

        String username2 = "davidase";
        assertEquals("davidase",userDao.getUserByUsername(username2).username);
        assertEquals("$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", userDao.getUserByUsername(username2).salt);
        assertEquals("$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy", userDao.getUserByUsername(username2).hashedPassword);

        String username3 = "federico";
        assertEquals("federico", userDao.getUserByUsername(username3).username);
        assertEquals("$2a$10$E3DgchtVry3qlYlzJCsyxe", userDao.getUserByUsername(username3).salt);
        assertEquals("$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2", userDao.getUserByUsername(username3).hashedPassword);

    }

    public void testGetAllUsers() {
        assertEquals(3, userDao.getAllUsers().size());

    }
}