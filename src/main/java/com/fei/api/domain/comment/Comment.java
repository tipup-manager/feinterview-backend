package com.fei.api.domain.comment;


import com.fei.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private Long userNumberId;

    @Column
    private Long postId;

    @Column
    private String comment;

    @Column
    private String userImg;

    @Column
    private int score;

    @Builder
    public Comment(
        String userId,
        Long userNumberId,
        String userImg,
        int score,
        Long postId,
        String comment
    ) {
        this.userId = userId;
        this.userNumberId = userNumberId;
        this.userImg = userImg;
        this.score = score;
        this.postId = postId;
        this.comment = comment;
    }
}
