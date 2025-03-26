package com.messaginghub.be.producer.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaginghub.be.producer.user.dto.UserRequestDto;
import com.messaginghub.be.producer.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/v1/auth")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRequestDto dto) {
        userService.register(dto);
        return ResponseEntity.ok().build();
    }

}
