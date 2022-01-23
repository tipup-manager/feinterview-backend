package com.fei.api.web.dto.post;

import com.fei.api.domain.post.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponseDto {

    private List<Post> list;
    private Long totalCount;
    private int pageNumber;

    public PostListResponseDto(List<Post> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
