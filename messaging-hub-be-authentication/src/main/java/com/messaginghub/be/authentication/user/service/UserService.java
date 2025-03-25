package com.messaginghub.be.authentication.user.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.messaginghub.be.authentication.user.details.UserDetailsImpl;
import com.messaginghub.be.authentication.user.dto.request.UserRequestDto;
import com.messaginghub.be.authentication.user.dto.response.UserListResponseDto;
import com.messaginghub.be.authentication.user.dto.response.UserLoginResponseDto;
import com.messaginghub.be.authentication.user.dto.response.UserRegisterResponseDto;
import com.messaginghub.be.core.model.User;
import com.messaginghub.be.core.model.enumerated.Role;
import com.messaginghub.be.authentication.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    public UserLoginResponseDto login(UserRequestDto dto) {
        Authentication request = UsernamePasswordAuthenticationToken.unauthenticated(dto.username(), dto.password());
        Authentication response = authenticationManager.authenticate(request);

        UserDetailsImpl details = (UserDetailsImpl) response.getPrincipal();

        Instant now = Instant.now();
        long expiry = 36000L;

        String scope = response.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(response.getName())
                .claim("scope", scope)
                .claim("principal", details.getUsername())
                .claim("id", details.getId())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new UserLoginResponseDto(token, details.getUsername(), scope);
    }

    public UserRegisterResponseDto register(UserRequestDto dto) {
        User user = User.builder()
            .username(dto.username())
            .password(passwordEncoder.encode(dto.password()))
            .role(Role.EMPLOYEE).build();
        userRepository.saveAndFlush(user);
        return new UserRegisterResponseDto("User created!");
    }

    public List<UserListResponseDto> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(UserListResponseDto::new)
            .toList();
    }

}
