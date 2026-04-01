package jobmatch.model;

import java.time.LocalDateTime;

public class User {

    private Integer userId;
    private String name;
    private String email;
    private Integer yearsExperience;
    private String desiredRole;
    private String location;
    private LocalDateTime createdAt;

    public  User() {}

    public User(Integer userId, String name, String email, Integer yearsExperience, String desiredRole, String location, LocalDateTime createdAt)  {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.yearsExperience = yearsExperience;
        this.desiredRole = desiredRole;
        this.location = location;
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Integer yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public String getDesiredRole() {
        return desiredRole;
    }

    public void setDesiredRole(String desiredRole) {
        this.desiredRole = desiredRole;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", yearsExperience=" + yearsExperience +
                ", desiredRole='" + desiredRole + '\'' +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
