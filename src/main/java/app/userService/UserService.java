package app.userService;

import app.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void saveUser(User user);

    void deleteUserById(long id);

    void updateUser(User user);
}
