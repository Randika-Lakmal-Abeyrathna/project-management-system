package me.randika.backend_service.user.entity;

import jakarta.persistence.Entity;
import me.randika.backend_service.base.BaseEntity;

@Entity(name = "permission")
public class PermissionEntity extends BaseEntity {
    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
