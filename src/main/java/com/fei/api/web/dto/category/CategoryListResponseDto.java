package com.fei.api.web.dto.category;

import com.fei.api.domain.category.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryListResponseDto {

    private List<Category> list;
    private Long totalCount;
    private int pageNumber;

    public CategoryListResponseDto(List<Category> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
