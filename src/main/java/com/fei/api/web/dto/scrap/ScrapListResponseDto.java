package com.fei.api.web.dto.scrap;

import com.fei.api.domain.scrap.Scrap;
import lombok.Getter;

import java.util.List;

@Getter
public class ScrapListResponseDto {

    private List<Scrap> list;
    private Long totalCount;
    private int pageNumber;

    public ScrapListResponseDto(List<Scrap> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
