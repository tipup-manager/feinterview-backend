package com.fei.api.web.dto.user;

import com.fei.api.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private final String token;
    private final String name;
    private final Long id;
    private final String userId;
    private final int age;
    private final String gender;
    private final String role;

    public UserResponseDto(User user, String token) {
        this.createdDate = user.getCreatedDate();
        this.modifiedDate = user.getModifiedDate();
        this.name = user.getName();
        this.id = user.getId();
        this.userId = user.getUserId();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.token = token;
        this.role = user.getRole();
    }
}
