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
    private String typeName;
    private String typeNumber;
    private String name;
    private String img;
    private Long index;
    private Long key;


    public CategoryResponseDto(Category category) {
        this.key = category.getKey();
        this.createdDate = category.getCreatedDate();
        this.modifiedDate = category.getModifiedDate();
        this.id = category.getId();
        this.img = category.getImg();
        this.typeName = category.getTypeName();
        this.typeNumber = category.getTypeNumber();
        this.name = category.getName();
        this.index = category.getIndex();
    }
}
