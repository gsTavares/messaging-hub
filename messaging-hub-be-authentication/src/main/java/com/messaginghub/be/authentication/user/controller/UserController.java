package com.messaginghub.be.authentication.user.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaginghub.be.authentication.user.dto.request.UserRequestDto;
import com.messaginghub.be.authentication.user.dto.response.UserLoginResponseDto;
import com.messaginghub.be.authentication.user.dto.response.UserRegisterResponseDto;
import com.messaginghub.be.authentication.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserRequestDto dto) {
        var response = userService.login(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Valid UserRequestDto dto) {
        var response = userService.register(dto);
        return ResponseEntity.ok(response);
    }
    

}
