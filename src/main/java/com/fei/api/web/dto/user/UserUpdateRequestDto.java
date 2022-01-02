package com.fei.api.web.dto.user;

import com.fei.api.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

   private String email;
   private String language;
   private String homePageUrl;
   private String githubUrl;
   private int career;
   private String userImg;
   private String title;
   private String content;
   private int issueCount;

    @Builder
    public UserUpdateRequestDto(
            String email,
            String language,
            String homePageUrl,
            String githubUrl,
            int career,
            String userImg,
            String title,
            String content,
            int issueCount
    ) {
        this.email = email;
        this.language = language;
        this.homePageUrl = homePageUrl;
        this.githubUrl = githubUrl;
        this.career = career;
        this.userImg = userImg;
        this.title = title;
        this.content = content;
        this.issueCount = issueCount;
    }

    public User toEntity() {
        return User.builder()
                .career(career)
                .content(content)
                .title(title)
                .email(email)
                .githubUrl(githubUrl)
                .homePageUrl(homePageUrl)
                .issueCount(issueCount)
                .language(language)
                .userImg(userImg)
                .build();

    }

}
