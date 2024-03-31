package app.userDao;

import app.model.User;

import java.util.List;


public interface UserDao {
    List<User> getAll();

    void saveUser(User user);

    void deleteUserById(long id);

    void updateUser(User user);
}
