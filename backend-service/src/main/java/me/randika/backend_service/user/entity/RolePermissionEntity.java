package me.randika.backend_service.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import me.randika.backend_service.base.BaseEntity;

@Entity(name = "role_permission")
public class RolePermissionEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private RoleEntity role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id",referencedColumnName = "id")
    private PermissionEntity permission;

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }
}
