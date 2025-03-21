package com.messaginghub.be.authentication.user.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaginghub.be.authentication.user.dto.request.UserRequestDto;
import com.messaginghub.be.authentication.user.service.UserService;
import com.messaginghub.be.authentication.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserRequestDto dto) {
        var response = userService.login(dto);
        return ResponseEntity.ok(new ApiResponse<>(response, null));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRequestDto dto) {
        var response = userService.register(dto);
        return ResponseEntity.ok(new ApiResponse<>(null, response.message()));
    }
    
    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        var response = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(response, null));
    }
    

}
