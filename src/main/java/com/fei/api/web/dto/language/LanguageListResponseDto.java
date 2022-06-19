package com.fei.api.web.dto.language;

import com.fei.api.domain.language.Language;
import lombok.Getter;

import java.util.List;

@Getter
public class LanguageListResponseDto {

    private List<Language> list;
    private Long totalCount;
    private int pageNumber;

    public LanguageListResponseDto(List<Language> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
