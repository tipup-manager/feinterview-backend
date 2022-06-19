package com.fei.api.web.dto.language;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LanguageInfoSaveRequestDto {

    private Long userNumberId;
    private String userEmail;
    private String userImg;
    private String categoryNames;
    private String categoryIds;
    private String userId;
    private String userMyPage;

    @Builder
    public LanguageInfoSaveRequestDto(
            Long userNumberId,
            String userEmail,
            String userImg,
            String categoryNames,
            String userId,
            String categoryIds,
            String userMyPage
    ) {
        this.userNumberId = userNumberId;
        this.userEmail = userEmail;
        this.userImg = userImg;
        this.categoryNames = categoryNames;
        this.userId = userId;
        this.categoryIds = categoryIds;
        this.userMyPage = userMyPage;
    }


}
