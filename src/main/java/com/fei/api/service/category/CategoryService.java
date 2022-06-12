package com.fei.api.service.category;

import com.fei.api.domain.category.Category;
import com.fei.api.domain.category.CategoryRepository;
import com.fei.api.web.dto.category.CategoryListResponseDto;
import com.fei.api.web.dto.category.CategorySaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(CategorySaveRequestDto categorySaveRequestDto) {
        categoryRepository.save(categorySaveRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public CategoryListResponseDto getCategory(Long categoryId, Pageable pageable) {
        Page<Category> pageData = categoryRepository.findCategoryList(pageable);
        return new CategoryListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }
}
