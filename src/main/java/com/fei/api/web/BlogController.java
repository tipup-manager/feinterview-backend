package com.fei.api.web;

import com.fei.api.service.blog.BlogService;
import com.fei.api.web.dto.blog.BlogListResponseDto;
import com.fei.api.web.dto.blog.BlogResponseDto;
import com.fei.api.web.dto.blog.BlogSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/api/v1/blog/{id}")
    public BlogResponseDto getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @PostMapping("/api/v1/blog")
    public BlogResponseDto save(@RequestBody BlogSaveRequestDto requestDto) {
        return blogService.save(requestDto);
    }

    @GetMapping("/api/v1/blog/list")
    public BlogListResponseDto getBlogs(@RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("CREATED_DATE").descending());
        return blogService.getBlogs(sortedByModifiedDateDesc);
    }

    @GetMapping("/api/v1/blog/list/{userNumberId}")
    public BlogListResponseDto getBlogsByUserNumberId(@PathVariable Long userNumberId, @RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("CREATED_DATE").descending());
        return blogService.getBlogsByUserNumberId(userNumberId, sortedByModifiedDateDesc);
    }

    @DeleteMapping("/api/v1/blog/{id}")
    public Long delete(@PathVariable Long id){
        blogService.delete(id);
        return id;
    }
}
