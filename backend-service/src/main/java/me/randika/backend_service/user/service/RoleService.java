package me.randika.backend_service.user.service;

import me.randika.backend_service.user.entity.RoleEntity;
import me.randika.backend_service.user.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity getRole(String roleName) {
        Optional<RoleEntity> byRole = roleRepository.findByRole(roleName);
        return byRole.orElseGet(() -> createRole(roleName));
    }

    public RoleEntity createRole(String roleName) {
        RoleEntity role = new RoleEntity();
        role.setRole(roleName);
        return roleRepository.save(role);
    }
}
