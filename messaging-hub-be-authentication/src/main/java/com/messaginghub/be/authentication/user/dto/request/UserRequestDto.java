package com.messaginghub.be.authentication.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotNull @NotBlank String username,
        @NotNull @NotBlank String password) {

}
