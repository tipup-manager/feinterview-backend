package com.fei.api.web.dto.comment;

import com.fei.api.domain.comment.Comment;
import com.fei.api.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String userId;
    private Long userNumberId;
    private Long postId;
    private String comment;
    private String userImg;
    private int score;
    private int recommentCount;

    @Builder
    public CommentSaveRequestDto(
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


    public Comment toEntity() {
        return Comment.builder()
                .userId(userId)
                .userNumberId(userNumberId)
                .userImg(userImg)
                .postId(postId)
                .score(score)
                .comment(comment)
                .build();

    }

}
