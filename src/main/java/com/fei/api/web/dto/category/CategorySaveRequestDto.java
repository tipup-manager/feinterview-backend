package com.fei.api.web.dto.category;

import com.fei.api.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {

    private String typeName;
    private String typeNumber;
    private String name;
    private String img;
    private Long index;

    @Builder
    public CategorySaveRequestDto(
            String typeName,
            String typeNumber,
            String img,
            String name,
            Long index
    ) {
        this.typeName = typeName;
        this.typeNumber = typeNumber;
        this.img = img;
        this.name = name;
        this.index = index;
    }


    public Category toEntity() {
        return Category.builder()
                .typeName(typeName)
                .typeNumber(typeNumber)
                .img(img)
                .name(name)
                .index(index)
                .build();

    }

}
