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
    private String loginType;
    private String oauthId;

    @Builder
    public UserLoginRequestDto(
            String userId,
            String pw,
            String loginType,
            String oauthId
    ) {
        this.userId = userId;
        this.pw = pw;
        this.loginType = loginType;
        this.oauthId = oauthId;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .pw(pw)
                .oauthId(oauthId)
                .build();

    }

}
