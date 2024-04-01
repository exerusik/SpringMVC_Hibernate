package app.userDao;

import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }


    @Transactional
    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}
