package com.fei.api.web;

import com.fei.api.service.comment.CommentService;
import com.fei.api.web.dto.comment.CommentListResponseDto;
import com.fei.api.web.dto.comment.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/v1/comment/{blogId}")
    public CommentListResponseDto getCommentByBlogId(@PathVariable Long blogId, @RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("createdDate").descending());
        return commentService.getCommentByBlogId(blogId, sortedByModifiedDateDesc);
    }

    @PostMapping("/api/v1/comment")
    public CommentListResponseDto save(@RequestBody CommentSaveRequestDto requestDto) {
        return commentService.save(requestDto);
    }


    @DeleteMapping("/api/v1/comment/{id}")
    public Long delete(@PathVariable Long id){
        commentService.delete(id);
        return id;
    }
}
