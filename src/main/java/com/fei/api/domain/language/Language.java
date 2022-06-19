package com.fei.api.domain.language;


import com.fei.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Language extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userNumberId;

    @Column
    private String userId;

    @Column
    private String userEmail;

    @Column
    private String userImg;

    @Column
    private String categoryName;

    @Column
    private String categoryId;

    @Column
    private String userMyPage;

    @Builder
    public Language(
            Long userNumberId,
        String userEmail,
        String userImg,
        String categoryName,
        String categoryId,
            String userId,
            String userMyPage
    ) {
        this.userNumberId = userNumberId;
        this.userEmail = userEmail;
        this.userImg = userImg;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.userId = userId;
        this.userMyPage = userMyPage;
    }
}
