package com.fei.api.web.dto.scrap;

import com.fei.api.domain.blog.Blog;
import com.fei.api.domain.scrap.Scrap;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ScrapSaveRequestDto {

    private Long blogId;
    private String writerImg;
    private String writerId;
    private Long userNumberId;
    private String title;
    private String category;

    @Builder
    public ScrapSaveRequestDto(
            Long blogId,
            String writerImg,
            String writerId,
            Long userNumberId,
            String title,
            String category
    ) {
        this.blogId = blogId;
        this.writerId = writerId;
        this.writerImg = writerImg;
        this.userNumberId = userNumberId;
        this.title = title;
        this.category = category;
    }

    public Scrap toEntity() {
        return Scrap.builder()
                .blogId(blogId)
                .writerId(writerId)
                .writerImg(writerImg)
                .userNumberId(userNumberId)
                .title(title)
                .category(category)
                .build();

    }

}
