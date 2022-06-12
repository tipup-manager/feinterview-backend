package com.fei.api.web;

import com.fei.api.service.category.CategoryService;
import com.fei.api.web.dto.category.CategoryListResponseDto;
import com.fei.api.web.dto.category.CategorySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/v1/category")
    public CategoryListResponseDto getCategory(@PathVariable Long postId, @RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("createdDate").descending());
        return categoryService.getCategory(postId, sortedByModifiedDateDesc);
    }

    @PostMapping("/api/v1/category")
    public void save(@RequestBody CategorySaveRequestDto requestDto) {
        categoryService.save(requestDto);
    }
}
