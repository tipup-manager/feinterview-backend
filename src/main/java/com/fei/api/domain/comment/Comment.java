package com.fei.api.domain.comment;


import com.fei.api.domain.BaseTimeEntity;
import com.fei.api.domain.user.User;
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

    @Column
    private int recommentCount;

    @Builder
    public Comment(
        String userId,
        Long userNumberId,
        String userImg,
        int score,
        Long postId,
        String comment,
        int recommentCount
    ) {
        this.userId = userId;
        this.userNumberId = userNumberId;
        this.userImg = userImg;
        this.score = score;
        this.postId = postId;
        this.comment = comment;
        this.recommentCount = recommentCount;
    }

    public Comment updateRecommentCount() {
        this.recommentCount = this.recommentCount + 1;
        return this;
    }

    public Comment updateRemoveRecommentCount() {
        this.recommentCount = this.recommentCount - 1;
        return this;
    }
}
