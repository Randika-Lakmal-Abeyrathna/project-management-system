package me.randika.backend_service.user.repo;

import me.randika.backend_service.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
