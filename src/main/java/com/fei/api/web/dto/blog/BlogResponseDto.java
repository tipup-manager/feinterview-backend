package com.fei.api.web.dto.blog;

import com.fei.api.domain.blog.Blog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BlogResponseDto {

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

    public BlogResponseDto(Blog blog) {
        this.createdDate = blog.getCreatedDate();
        this.modifiedDate = blog.getModifiedDate();
        this.userId = blog.getUserId();
        this.userNumberId = blog.getUserNumberId();
        this.userImg = blog.getUserImg();
        this.starCount = blog.getStarCount();
        this.starCountInfo = blog.getStarCountInfo();
        this.isFree = blog.getIsFree();
        this.price = blog.getPrice();
        this.badges = blog.getBadges();
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.category = blog.getCategory();
        this.mainImg = blog.getMainImg();
    }
}
