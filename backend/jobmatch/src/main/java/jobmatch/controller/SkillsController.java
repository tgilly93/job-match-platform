package jobmatch.controller;

import jobmatch.model.Skills;
import jobmatch.service.SkillsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillsController {

    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping
    public List<Skills> getAllSkills() {
        return skillsService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skills getSkillById(@PathVariable int id) {
        return skillsService.getSkillById(id);
    }

    @PostMapping
    public Integer addSkill(@RequestBody Skills skill) {
        return skillsService.addSkill(skill);
    }
}
