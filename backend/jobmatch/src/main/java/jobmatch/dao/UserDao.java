package jobmatch.dao;

import jobmatch.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(int userId);

    int addUser(User user);

    void updateUser(User user);

    void deleteUser(int userId);

}
