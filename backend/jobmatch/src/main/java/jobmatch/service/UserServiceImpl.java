package jobmatch.service;

import jobmatch.dao.UserDao;
import jobmatch.dto.UserSkillDto;
import jobmatch.model.User;
import jobmatch.model.UserSkill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void addUserSkill(int userId, UserSkillDto dto) {

        UserSkill userSkill = new UserSkill();
        userSkill.setUserId(userId);
        userSkill.setSkillId(dto.getSkillId());
        userSkill.setProficiencyLevel(dto.getProficiencyLevel());

        int rows = userDao.addUserSkill(userSkill);

        if (rows == 0) {
            throw new RuntimeException("User skill insert/update failed");
        }
    }

    @Override
    public List<UserSkill>  getUserSkills(int userId) {
        return userDao.getUserSkills(userId);
    }
}
