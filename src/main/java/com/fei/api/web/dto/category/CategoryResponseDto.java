package com.fei.api.web.dto.category;

import com.fei.api.domain.category.Category;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CategoryResponseDto {

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private final Long id;
    private String type;
    private String krName;
    private String enName;
    private Long index;


    public CategoryResponseDto(Category category) {
        this.createdDate = category.getCreatedDate();
        this.modifiedDate = category.getModifiedDate();
        this.id = category.getId();
        this.type = category.getType();
        this.krName = category.getKrName();
        this.enName = category.getEnName();
        this.index = category.getIndex();
    }
}
