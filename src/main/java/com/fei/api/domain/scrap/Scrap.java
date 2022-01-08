package com.fei.api.domain.scrap;


import com.fei.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Scrap extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long blogId;

    @Column
    private String writerImg;

    @Column
    private String writerId;

    @Column
    private Long userNumberId;

    @Column
    private String title;

    @Column
    private String category;

    @Builder
    public Scrap(
        Long blogId,
        String writerImg,
        String writerId,
        Long userNumberId,
        String title,
        String category
    ) {
        this.blogId = blogId;
        this.writerId = writerId;
        this.writerImg = writerImg;
        this.userNumberId = userNumberId;
        this.title = title;
        this.category = category;
    }
}
