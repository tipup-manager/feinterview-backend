package com.fei.api.web.dto.recomment;

import com.fei.api.domain.recomment.ReComment;
import lombok.Getter;

import java.util.List;

@Getter
public class ReCommentListResponseDto {

    private List<ReComment> list;
    private Long totalCount;
    private int pageNumber;

    public ReCommentListResponseDto(List<ReComment> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
