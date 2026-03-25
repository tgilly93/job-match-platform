package jobmatch.service;

import jobmatch.dao.SkillsDao;
import jobmatch.model.Skills;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsServiceImpl implements SkillsService {

    private final SkillsDao skillsDao;

    public SkillsServiceImpl(SkillsDao skillsDao) {
        this.skillsDao = skillsDao;
    }

    @Override
    public List<Skills> getAllSkills() {
        return skillsDao.getAllSkills();
    }

    @Override
    public Skills getSkillById(int skillId) {
        return skillsDao.getSkillById(skillId);
    }

    @Override
    public Integer addSkill(Skills skill) {
        return skillsDao.addSkill(skill);
    }
}
