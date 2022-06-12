package com.fei.api.web.dto.category;

import com.fei.api.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {

    private String type;
    private String krName;
    private String enName;
    private Long index;

    @Builder
    public CategorySaveRequestDto(
            String type,
            String krName,
            String enName,
            Long index
    ) {
        this.type = type;
        this.krName = krName;
        this.enName = enName;
        this.index = index;
    }


    public Category toEntity() {
        return Category.builder()
                .type(type)
                .enName(enName)
                .krName(krName)
                .index(index)
                .build();

    }

}
