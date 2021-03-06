package com.fei.api.web;

import com.fei.api.service.post.PostService;
import com.fei.api.web.dto.post.PostListResponseDto;
import com.fei.api.web.dto.post.PostResponseDto;
import com.fei.api.web.dto.post.PostResponseWithoutUserDto;
import com.fei.api.web.dto.post.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/api/v1/post/category/{category}")
    public PostListResponseDto getPostByCategory(@PathVariable String category, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return postService.getPostByCategory(category, sortedByModifiedDateDesc);
    }

    @PostMapping("/api/v1/post")
    public PostResponseDto save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @GetMapping("/api/v1/post/list")
    public PostListResponseDto getPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "0") int size) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, size, Sort.by("CREATED_DATE").descending());
        return postService.getPosts(sortedByModifiedDateDesc);
    }

    @GetMapping("/api/v1/post/list/{userNumberId}")
    public PostListResponseDto getPostsByUserNumberId(@PathVariable Long userNumberId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "12") int size) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return postService.getPostsByUserNumberId(userNumberId, sortedByModifiedDateDesc);
    }

    @DeleteMapping("/api/v1/post/{id}")
    public Long delete(@PathVariable Long id){
        postService.delete(id);
        return id;
    }
}
