package me.randika.backend_service.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import me.randika.backend_service.base.BaseEntity;
import me.randika.backend_service.project.entity.ProjectEntity;
import me.randika.backend_service.task.enums.TaskPriority;
import me.randika.backend_service.task.enums.TaskStatus;
import me.randika.backend_service.user.entity.UserEntity;

@Entity(name = "task")
public class TaskEntity extends BaseEntity {
    private String topic;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user",referencedColumnName = "id",nullable = true)
    private UserEntity assignedUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project",referencedColumnName = "id",nullable = false)
    private ProjectEntity project;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserEntity getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserEntity assignedUser) {
        this.assignedUser = assignedUser;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }
}
