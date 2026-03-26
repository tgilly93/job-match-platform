package jobmatch.service;

import jobmatch.model.Skills;

import java.util.List;

public interface SkillsService {

    List<Skills> getAllSkills();

    Skills getSkillById(int skillId);

    Integer addSkill(Skills skill);

    Skills getOrCreateSkill(String name);
}
