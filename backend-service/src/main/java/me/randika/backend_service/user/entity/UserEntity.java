package me.randika.backend_service.user.entity;

import jakarta.persistence.*;
import me.randika.backend_service.user.enums.UserStatus;
import me.randika.backend_service.user.enums.UserType;
import me.randika.backend_service.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "app_user")
public class UserEntity extends BaseEntity {
    private String firstName;
    private String lastNamee;
    private String email;
    private String password;
    @Column(unique = true)
    private String username;
    private UserType userType;
    private UserStatus userStatus;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_user_roles",
        joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(String firstName, String lastNamee, String email, String password, String username, UserType userType, UserStatus userStatus) {
        this.firstName = firstName;
        this.lastNamee = lastNamee;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userType = userType;
        this.userStatus = userStatus;
    }

    public UserEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNamee() {
        return lastNamee;
    }

    public void setLastNamee(String lastNamee) {
        this.lastNamee = lastNamee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
