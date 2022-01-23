package com.fei.api.web.dto.scrap;

import com.fei.api.domain.scrap.Scrap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScrapResponseDto {

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private final Long id;
    private Long blogId;
    private String writerImg;
    private String writerId;
    private Long userNumberId;
    private String title;
    private String category;

    public ScrapResponseDto(Scrap scrap) {
        this.createdDate = scrap.getCreatedDate();
        this.modifiedDate = scrap.getModifiedDate();
        this.userNumberId = scrap.getUserNumberId();
        this.id = scrap.getId();
        this.title = scrap.getTitle();
        this.category = scrap.getCategory();
        this.blogId = scrap.getBlogId();
        this.writerId = scrap.getWriterId();
        this.writerImg = scrap.getWriterImg();
    }
}
