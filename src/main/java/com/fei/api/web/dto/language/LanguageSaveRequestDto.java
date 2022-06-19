package com.fei.api.web.dto.language;

import com.fei.api.domain.language.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LanguageSaveRequestDto {

    private Long userNumberId;
    private String userEmail;
    private String userImg;
    private String categoryName;
    private String categoryId;
    private String userId;
    private String userMyPage;

    @Builder
    public LanguageSaveRequestDto(
            Long userNumberId,
            String userEmail,
            String userImg,
            String categoryName,
            String userId,
            String categoryId,
            String userMyPage
    ) {
        this.userNumberId = userNumberId;
        this.userEmail = userEmail;
        this.userImg = userImg;
        this.categoryName = categoryName;
        this.userId = userId;
        this.categoryId = categoryId;
        this.userMyPage = userMyPage;
    }


    public Language toEntity() {
        return Language.builder()
                .userNumberId(userNumberId)
                .userEmail(userEmail)
                .userImg(userImg)
                .categoryName(categoryName)
                .userId(userId)
                .categoryId(categoryId)
                .userMyPage(userMyPage)
                .build();

    }

}
