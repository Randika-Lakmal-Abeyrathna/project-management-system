package me.randika.backend_service.project.entity;

import jakarta.persistence.*;
import me.randika.backend_service.base.BaseEntity;
import me.randika.backend_service.project.enums.ProjectStatus;
import me.randika.backend_service.user.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "project")
public class ProjectEntity extends BaseEntity {

    private String projectName;
    private String description;
    private ProjectStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_owner",referencedColumnName = "id")
    private UserEntity projectOwner;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_app_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id")
    )
    private Set<UserEntity> assignedUsers = new HashSet<>();

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public UserEntity getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(UserEntity projectOwner) {
        this.projectOwner = projectOwner;
    }

    public Set<UserEntity> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(Set<UserEntity> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
}
