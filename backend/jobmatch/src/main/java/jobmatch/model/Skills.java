package jobmatch.model;

public class Skills {

    private Integer skillId;
    private String name;

    public Skills() {}

    public Skills(Integer skillId,  String name) {
        this.skillId = skillId;
        this.name = name;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "skillId=" + skillId +
                ", name='" + name + '\'' +
                '}';
    }
}
