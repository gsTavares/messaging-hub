package com.messaginghub.be.authentication.user.dto.response;

public record UserLoginResponseDto(String access_token, String user, String scope) {
    
}
