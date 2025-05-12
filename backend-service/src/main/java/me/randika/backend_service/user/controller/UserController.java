package me.randika.backend_service.user.controller;

import jakarta.validation.Valid;
import me.randika.backend_service.base.ApiResponse;
import me.randika.backend_service.base.PaginatedResponse;
import me.randika.backend_service.user.dto.UserRequestDto;
import me.randika.backend_service.user.dto.UserResponseDto;
import me.randika.backend_service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/app-user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<PaginatedResponse<UserResponseDto>>> getAllUsers(
            @RequestParam(name = "page",defaultValue = "1",required = false) int page,
            @RequestParam(name = "offset",defaultValue = "10",required = false) int offset,
            @RequestParam(name = "sortBy",defaultValue = "id") String sortBy
                                    ) {

        return userService.getAllUsers(PageRequest.of(page - 1, offset, Sort.by(sortBy).ascending()));
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserResponseDto>> create(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

}
