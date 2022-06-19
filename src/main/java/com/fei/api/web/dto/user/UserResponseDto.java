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
    private final Long id;
    private final String userId;
    private final String role;
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

    public UserResponseDto(User user, String token) {
        this.createdDate = user.getCreatedDate();
        this.modifiedDate = user.getModifiedDate();
        this.id = user.getId();
        this.userId = user.getUserId();
        this.token = token;
        this.role = user.getRole();
        this.email = user.getEmail();
        this.languageName = user.getLanguageName();
        this.languageNumber = user.getLanguageNumber();
        this.homePageUrl = user.getHomePageUrl();
        this.githubUrl = user.getGithubUrl();
        this.career = user.getCareer();
        this.signupMethod = user.getSignupMethod();
        this.userImg = user.getUserImg();
        this.title = user.getTitle();
        this.content = user.getContent();
        this.issueCount = user.getIssueCount();
    }
}
