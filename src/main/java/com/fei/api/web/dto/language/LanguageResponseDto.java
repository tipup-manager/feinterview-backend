package com.fei.api.web.dto.language;

import com.fei.api.domain.category.Category;
import com.fei.api.domain.language.Language;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class LanguageResponseDto {

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private Long id;
    private Long userNumberId;
    private String userEmail;
    private String userImg;
    private String categoryName;
    private String categoryId;
    private String userId;
    private String userMyPage;


    public LanguageResponseDto(Language language) {
        this.createdDate = language.getCreatedDate();
        this.modifiedDate = language.getModifiedDate();
        this.id = language.getId();
        this.userNumberId = language.getUserNumberId();
        this.userEmail = language.getUserEmail();
        this.userImg = language.getUserImg();
        this.categoryName = language.getCategoryName();
        this.categoryId = language.getCategoryId();
        this.userId = language.getUserId();
        this.userMyPage = language.getUserMyPage();
    }
}
