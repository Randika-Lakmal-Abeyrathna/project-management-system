package me.randika.backend_service.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import me.randika.backend_service.user.enums.UserStatus;
import me.randika.backend_service.user.enums.UserType;

public record UserRequestDto(
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotBlank(message = "First Name is mandatory")
        String firstName,
        String lastName,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8,message = "Password must be at least 8 characters longs")
        String password,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Incorrect Email format")
        String email,
        UserType userType,
        UserStatus userStatus
        ) {
}
