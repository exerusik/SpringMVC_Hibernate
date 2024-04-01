package app.userService;

import app.model.User;
import app.userDao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
}