package pp_312.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pp_312.dao.UserDao;
import pp_312.model.User;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    public List<User> readAll() {
        return userDao.readAll();
    }

    public User readOne(int id) {
        return userDao.readOne(id);
    }

    @Transactional
    public void update(int id,User updatedUser) {
        userDao.update(id, updatedUser);

    }
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional
    public void truncateAll(){
        userDao.truncateAll();
    }
}