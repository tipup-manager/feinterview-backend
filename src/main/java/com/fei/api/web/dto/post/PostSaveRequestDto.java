package com.fei.api.web.dto.post;

import com.fei.api.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

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

    @Builder
    public PostSaveRequestDto(
            String userId,
            Long userNumberId,
            String title,
            String content,
            String userImg,
            int starCount,
            String starCountInfo,
            String isFree,
            int price,
            String badges,
            String category,
            String mainImg
    ) {
        this.userId = userId;
        this.userNumberId = userNumberId;
        this.userImg = userImg;
        this.title = title;
        this.content = content;
        this.starCount = starCount;
        this.starCountInfo = starCountInfo;
        this.isFree = isFree;
        this.price = price;
        this.badges = badges;
        this.category = category;
        this.mainImg = mainImg;
    }

    public Post toEntity() {
        return Post.builder()
                .userId(userId)
                .userNumberId(userNumberId)
                .content(content)
                .title(title)
                .userImg(userImg)
                .starCount(starCount)
                .starCountInfo(starCountInfo)
                .isFree(isFree)
                .price(price)
                .badges(badges)
                .category(category)
                .mainImg(mainImg)
                .build();

    }

}
