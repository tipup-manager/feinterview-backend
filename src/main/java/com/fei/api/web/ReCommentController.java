package com.fei.api.web;

import com.fei.api.service.recomment.ReCommentService;
import com.fei.api.web.dto.recomment.ReCommentListResponseDto;
import com.fei.api.web.dto.recomment.ReCommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ReCommentController {

    private final ReCommentService reCommentService;

    @GetMapping("/api/v1/recomment/{commentId}")
    public ReCommentListResponseDto getReCommentByBlogId(@PathVariable Long commentId, @RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("createdDate").descending());
        return reCommentService.getReCommentByBlogId(commentId, sortedByModifiedDateDesc);
    }

    @PostMapping("/api/v1/recomment")
    public ReCommentListResponseDto save(@RequestBody ReCommentSaveRequestDto requestDto) {
        return reCommentService.save(requestDto);
    }


    @DeleteMapping("/api/v1/recomment/{id}")
    public Long delete(@PathVariable Long id){
        reCommentService.delete(id);
        return id;
    }
}
