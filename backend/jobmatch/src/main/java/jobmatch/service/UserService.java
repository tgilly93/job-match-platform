package jobmatch.service;

import jobmatch.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    int addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
