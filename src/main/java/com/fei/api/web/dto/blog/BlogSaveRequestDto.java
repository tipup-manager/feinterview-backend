package com.fei.api.web.dto.blog;

import com.fei.api.domain.blog.Blog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlogSaveRequestDto {

    private String userId;
    private Long userNumberId;
    private String title;
    private String content;
    private String userImg;
    private String starCountInfo;
    private int starCount;
    private String addIds;
    private String scrapIds;
    private String isFree;
    private int price;
    private String badges;

    @Builder
    public BlogSaveRequestDto(
            String userId,
            Long userNumberId,
            String title,
            String content,
            String userImg,
            int starCount,
            String starCountInfo,
            String addIds,
            String scrapIds,
            String isFree,
            int price,
            String badges
    ) {
        this.userId = userId;
        this.userNumberId = userNumberId;
        this.userImg = userImg;
        this.title = title;
        this.content = content;
        this.starCount = starCount;
        this.starCountInfo = starCountInfo;
        this.addIds = addIds;
        this.scrapIds = scrapIds;
        this.isFree = isFree;
        this.price = price;
        this.badges = badges;
    }

    public Blog toEntity() {
        return Blog.builder()
                .userId(userId)
                .userNumberId(userNumberId)
                .content(content)
                .title(title)
                .userImg(userImg)
                .starCount(starCount)
                .starCountInfo(starCountInfo)
                .addIds(addIds)
                .scrapIds(scrapIds)
                .isFree(isFree)
                .price(price)
                .badges(badges)
                .build();

    }

}
