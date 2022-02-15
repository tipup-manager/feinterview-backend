package com.fei.api.domain.recomment;


import com.fei.api.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ReComment extends BaseTimeEntity {

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
    private Long commentId;

    @Column
    private String comment;

    @Column
    private String userImg;

    @Builder
    public ReComment(
        String userId,
        Long userNumberId,
        String userImg,
        Long postId,
        String comment,
        Long commentId
    ) {
        this.userId = userId;
        this.userNumberId = userNumberId;
        this.userImg = userImg;
        this.postId = postId;
        this.comment = comment;
        this.commentId = commentId;
    }

}
