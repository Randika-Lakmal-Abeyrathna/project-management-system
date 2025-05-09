package me.randika.backend_service.user.entity;

import jakarta.persistence.*;
import me.randika.backend_service.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "role")
public class RoleEntity extends BaseEntity {
    private String role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions = new HashSet<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }
}
