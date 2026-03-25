package jobmatch.dao;

import jobmatch.model.Skills;

import java.util.List;

public interface SkillsDao {

    List<Skills> getAllSkills();

    Skills getSkillById(int skillId);

    Integer addSkill(Skills skill);
}
