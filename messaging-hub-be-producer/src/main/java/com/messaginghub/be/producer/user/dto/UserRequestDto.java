package com.messaginghub.be.producer.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotNull(message = "Username is required") @NotBlank(message = "Username can't be empty") String username,
        @NotNull(message = "Password is required") @NotBlank(message = "Password can't be empty") String password) {

}
