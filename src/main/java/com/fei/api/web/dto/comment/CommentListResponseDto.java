package com.fei.api.web.dto.comment;

import com.fei.api.domain.comment.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponseDto {

    private List<Comment> list;
    private Long totalCount;
    private int pageNumber;

    public CommentListResponseDto(List<Comment> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
