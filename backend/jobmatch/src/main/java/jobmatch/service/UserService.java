package jobmatch.service;

import jobmatch.dto.UserSkillDto;
import jobmatch.model.User;
import jobmatch.model.UserSkill;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    int addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    void addUserSkill(int userId, UserSkillDto dto);

    List<UserSkill> getUserSkills(int userId);
}
