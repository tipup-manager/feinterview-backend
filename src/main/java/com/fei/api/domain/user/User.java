package com.fei.api.domain.user;


import com.fei.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String pw;

    @Column
    private String role;

    @Column
    private String email;

    @Column
    private String homePageUrl;

    @Column
    private String githubUrl;

    @Column
    private int career;

    @Column
    private String languageNumber;

    @Column
    private String languageName;

    @Column
    private String signupMethod;

    @Column
    private String userImg;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int issueCount;

    @Column
    private String oauthId;

    @Builder
    public User(
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

    public User update(User user) {
        this.email = user.getEmail();
        this.languageNumber = user.getLanguageNumber();
        this.languageName = user.getLanguageName();
        this.homePageUrl = user.getHomePageUrl();
        this.githubUrl = user.getGithubUrl();
        this.career = user.getCareer();
        this.userImg = user.getUserImg();
        this.title = user.getTitle();
        this.content = user.getContent();
        this.issueCount = user.getIssueCount();
        return this;
    }
}
