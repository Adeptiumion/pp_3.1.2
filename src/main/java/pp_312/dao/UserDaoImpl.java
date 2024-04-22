package pp_312.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pp_312.model.User;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public List<User> readAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User readOne(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(int id, User user) {
        User updatedUser = entityManager.find(User.class, id);
        updatedUser.setName(user.getName());
        entityManager.flush();
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
        entityManager.flush();
    }

    @Override
    public User readByName(String name) {
        User user;
        try {
            user = (User) entityManager
                    .createQuery("from User u where name=:n")
                    .setParameter("n", name)
                    .getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
        return user;
    }

    @Override
    public void truncateAll() {
        entityManager.createNativeQuery("truncate table user").executeUpdate();
        entityManager.flush();
    }
}
