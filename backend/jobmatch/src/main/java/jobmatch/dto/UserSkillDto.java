package jobmatch.dto;

public class UserSkillDto {

    private int skillId;
    private int proficiencyLevel;

    public UserSkillDto() {}

    public UserSkillDto(int skillId, int proficiencyLevel) {
        this.skillId = skillId;
        this.proficiencyLevel = proficiencyLevel;
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
        return "UserSkillDto{" +
                "skillId=" + skillId +
                ", proficiencyLevel=" + proficiencyLevel +
                '}';
    }
}
