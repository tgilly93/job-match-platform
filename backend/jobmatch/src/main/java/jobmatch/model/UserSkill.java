package jobmatch.model;

public class UserSkill {

    private int userId;
    private int skillId;
    private int proficiencyLevel;

    public UserSkill() {}

    public UserSkill(int userId, int skillId, int proficiencyLevel) {
        this.userId = userId;
        this.skillId = skillId;
        this.proficiencyLevel = proficiencyLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(int proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    @Override
    public String toString() {
        return "UserSkill{" +
                "userId=" + userId +
                ", skillId=" + skillId +
                ", proficiencyLevel=" + proficiencyLevel +
                '}';
    }
}
