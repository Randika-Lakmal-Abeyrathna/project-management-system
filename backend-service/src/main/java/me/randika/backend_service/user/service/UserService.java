package me.randika.backend_service.user.service;

import me.randika.backend_service.base.ApiResponse;
import me.randika.backend_service.base.PaginatedResponse;
import me.randika.backend_service.user.dto.UserRequestDto;
import me.randika.backend_service.user.dto.UserResponseDto;
import me.randika.backend_service.user.entity.UserEntity;
import me.randika.backend_service.user.repo.UserRepository;
import me.randika.backend_service.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository
    , RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public ResponseEntity<ApiResponse<PaginatedResponse<UserResponseDto>>> getAllUsers(Pageable pageable) {

        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        List<UserResponseDto> userResponseDto = mapResponseToDto(userEntityPage);

        PaginatedResponse<UserResponseDto> paginatedResponse = new PaginatedResponse<>(
                userResponseDto,
                userEntityPage.getSize(),
                userEntityPage.getTotalElements(),
                userEntityPage.getTotalPages()
        );

        return ApiResponse.success(paginatedResponse, "Users retrieved successfully", HttpStatus.OK);
    }


    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(UserRequestDto userRequestDto){
        // Check if username already exists
        if (userRepository.existsByUsername(userRequestDto.username())) {
            return ApiResponse.error("Username already exists", HttpStatus.CONFLICT);
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userRequestDto.email())) {
            return ApiResponse.error("Email already exists", HttpStatus.CONFLICT);
        }

        try {
            // Map the request DTO to entity
            UserEntity userEntity = mapResponseToEntity(userRequestDto);

            // Set Default Role as USER
            userEntity.setRoles(Set.of(roleService.getRole("USER")));

            // Hash the password before saving
            userEntity.setPassword(PasswordUtils.hashPassword(userRequestDto.password()));

            // Save the entity to the database
            UserEntity savedUser = userRepository.save(userEntity);

            // Map the saved entity to response DTO
            UserResponseDto responseDto = mapEntityToDto(savedUser);

            // Return the response with HTTP 201 Created status
            return ApiResponse.success(responseDto, "User created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ApiResponse.error("Error creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private UserEntity mapResponseToEntity(UserRequestDto userRequestDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDto.username());
        userEntity.setFirstName(userRequestDto.firstName());
        userEntity.setLastNamee(userRequestDto.lastName());
        userEntity.setPassword(userRequestDto.password());
        userEntity.setEmail(userRequestDto.email());
        userEntity.setUserType(userRequestDto.userType());
        userEntity.setUserStatus(userRequestDto.userStatus());

        return userEntity;
    }

    /**
     * Maps a UserEntity to a UserResponseDto
     * 
     * @param user The user entity to map
     * @return The mapped user response DTO
     */
    private UserResponseDto mapEntityToDto(UserEntity user) {
        return new UserResponseDto(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastNamee(),
            user.getEmail(),
            user.getUserType(),
            user.getUserStatus()
        );
    }

    /**
     *  Maps a Page<UserEntity>> to a List of UserResponseDto
     *
     * @param users User list
     * @return List<UserResponseDto>
     */
    private List<UserResponseDto> mapResponseToDto(Page<UserEntity> users) {
        return users.getContent().stream()
                .map(this::mapEntityToDto)
                .toList();
    }

}
