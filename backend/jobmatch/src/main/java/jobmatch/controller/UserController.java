package jobmatch.controller;

import jobmatch.dto.UserSkillDto;
import jobmatch.model.User;
import jobmatch.model.UserSkill;
import jobmatch.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id)  {
        User user = userService.getUserById(id);

        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

    @PostMapping
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        user.setUserId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{userId}/skills")
    public void addUserSkill(@PathVariable int userId, @RequestBody UserSkillDto dto) {
        userService.addUserSkill(userId, dto);
    }

    @GetMapping("/{userId}/skills")
    public List<UserSkill> getUserSkills(@PathVariable int userId) {
        return userService.getUserSkills(userId);
    }
}
