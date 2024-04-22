package pp_312.dao;


import pp_312.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    List<User> readAll();

    User readOne(int id);

    void update(int id, User updatedUser);

    void delete(int id);
    User readByName(String name);

    void truncateAll();
}
