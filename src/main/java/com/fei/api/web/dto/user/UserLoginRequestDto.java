package com.fei.api.web.dto.user;

import com.fei.api.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {

    private String userId;
    private String pw;

    @Builder
    public UserLoginRequestDto(
            String userId,
            String pw
    ) {
        this.userId = userId;
        this.pw = pw;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .pw(pw)
                .build();

    }

}
