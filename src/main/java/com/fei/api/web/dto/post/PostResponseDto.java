package com.fei.api.web.dto.post;

import com.fei.api.domain.post.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostResponseDto {

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private final Long id;
    private String userId;
    private Long userNumberId;
    private String title;
    private String content;
    private String userImg;
    private String starCountInfo;
    private int starCount;
    private String isFree;
    private int price;
    private String badges;
    private String category;
    private String mainImg;

    public PostResponseDto(Post post) {
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        this.userId = post.getUserId();
        this.userNumberId = post.getUserNumberId();
        this.userImg = post.getUserImg();
        this.starCount = post.getStarCount();
        this.starCountInfo = post.getStarCountInfo();
        this.isFree = post.getIsFree();
        this.price = post.getPrice();
        this.badges = post.getBadges();
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.mainImg = post.getMainImg();
    }
}
