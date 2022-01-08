package com.fei.api.web.dto.blog;

import com.fei.api.domain.blog.Blog;
import lombok.Getter;

import java.util.List;

@Getter
public class BlogListResponseDto {

    private List<Blog> list;
    private Long totalCount;
    private int pageNumber;

    public BlogListResponseDto(List<Blog> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
