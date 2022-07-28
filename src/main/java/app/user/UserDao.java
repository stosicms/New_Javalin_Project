package app.user;

import java.util.List;

public class UserDao {

    private final List<User> users = List.of(
            new User("perwendel", "$2a$10$h.dl5J86rGH7I8bD9bZeZe", "$2a$10$h.dl5J86rGH7I8bD9bZeZeci0pDt0.VwFTGujlnEaZXPf/q7vM5wO"),
            new User("davidase", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe", "$2a$10$e0MYzXyjpJS7Pd0RVvHwHe1HlCS4bZJ18JuywdEMLT83E1KDmUhCy"),
            new User("federico", "$2a$10$E3DgchtVry3qlYlzJCsyxe", "$2a$10$E3DgchtVry3qlYlzJCsyxeSK0fftK4v0ynetVCuDdxGVl1obL.ln2"),
            new User("milos", "", "03aa81d44d00b6d38f47005b11e34fd0c04f72ed94451bafc48d2dcf2e56e697")
    );

    public User getUserByUsername (String username) {
        for (User user:users) {
            if (username.equals(user.username)){
                return user;
            }
        }
        return null;
    }
    public List<User> getAllUsers () {
        return users;
    }
}
