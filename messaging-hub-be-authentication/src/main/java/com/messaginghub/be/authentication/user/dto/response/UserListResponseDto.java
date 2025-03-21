package com.messaginghub.be.authentication.user.dto.response;

import com.messaginghub.be.authentication.user.model.User;
import com.messaginghub.be.authentication.user.model.enumerated.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListResponseDto {
    
    private String id;
    private String username;
    private Role role;

    public UserListResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

}
