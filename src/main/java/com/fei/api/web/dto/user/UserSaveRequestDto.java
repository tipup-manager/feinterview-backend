package com.fei.api.web.dto.user;

import com.fei.api.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

   private String userId;
   private String pw;
   private String role;
   private String email;
   private String languageNumber;
    private String languageName;
   private String homePageUrl;
   private String githubUrl;
   private int career;
   private String signupMethod;
   private String userImg;
   private String title;
   private String content;
   private int issueCount;
   private String oauthId;

    @Builder
    public UserSaveRequestDto(
            String userId,
            String pw,
            String role,
            String email,
            String languageNumber,
            String languageName,
            String homePageUrl,
            String githubUrl,
            int career,
            String signupMethod,
            String userImg,
            String title,
            String content,
            int issueCount,
            String oauthId
    ) {
        this.userId = userId;
        this.pw = pw;
        this.role = role;
        this.email = email;
        this.languageNumber = languageNumber;
        this.languageName = languageName;
        this.homePageUrl = homePageUrl;
        this.githubUrl = githubUrl;
        this.career = career;
        this.signupMethod = signupMethod;
        this.userImg = userImg;
        this.title = title;
        this.content = content;
        this.issueCount = issueCount;
        this.oauthId = oauthId;
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .pw(pw)
                .role(role)
                .career(career)
                .content(content)
                .title(title)
                .email(email)
                .githubUrl(githubUrl)
                .homePageUrl(homePageUrl)
                .issueCount(issueCount)
                .languageNumber(languageNumber)
                .languageName(languageName)
                .signupMethod(signupMethod)
                .userImg(userImg)
                .oauthId(oauthId)
                .build();

    }

}
