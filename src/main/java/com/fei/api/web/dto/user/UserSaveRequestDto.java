package com.fei.api.web.dto.user;

import com.fei.api.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String name;
    private String userId;
    private int age;
    private String gender;
    private String pw;
    private String role;

    @Builder
    public UserSaveRequestDto(
            String name,
            String userId,
            int age,
            String gender,
            String pw,
            String role
    ) {
        this.name = name;
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.pw = pw;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .userId(userId)
                .age(age)
                .gender(gender)
                .pw(pw)
                .role(role)
                .build();

    }

}
