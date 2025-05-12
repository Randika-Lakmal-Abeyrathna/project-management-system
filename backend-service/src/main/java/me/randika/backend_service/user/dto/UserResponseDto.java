package me.randika.backend_service.user.dto;

import me.randika.backend_service.user.enums.UserStatus;
import me.randika.backend_service.user.enums.UserType;

import java.util.UUID;

public record UserResponseDto(UUID id, String username, String firstName, String lastName,
                              String email, UserType userType, UserStatus userStatus) {
}
