package app.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
    //private static UserDao userDao;
    private static final List<User> users = new ArrayList<>();

    public UserDao() {
//        users.add(new User("perwendel", "$2a$10$ItiCBD2OlV6mUPXwSjjxIO", "$2a$10$ItiCBD2OlV6mUPXwSjjxIOap5I4I//hcajYnsDBScWSS4E0njQJpi"));
//        users.add(new User("davidase", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHet/rLSgB1vMGfq0pbysLpipYIXFoZpVC"));
//        users.add(new User("federico", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2"));
    }

//    public static UserDao getInstance() {
//        synchronized (UserDao.class) {
//            if(userDao == null) {
//                userDao = new UserDao();
//            }
//        }
//        return userDao;
//    }

    public User getUserByUsername(String username) {
        return users.stream().filter(u -> u.username.equals(username)).findFirst().orElse(null);
    }

    public Iterable<String> getAllUserNames() {
        return users.stream().map(user -> user.username).collect(Collectors.toList());
    }

    public void saveOneUser(User user) {
        users.add(user);
    }


}
