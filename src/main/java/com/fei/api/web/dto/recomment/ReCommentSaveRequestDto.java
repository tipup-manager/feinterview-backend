package com.fei.api.web.dto.recomment;

import com.fei.api.domain.comment.Comment;
import com.fei.api.domain.recomment.ReComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReCommentSaveRequestDto {

    private String userId;
    private Long userNumberId;
    private Long postId;
    private Long commentId;
    private String comment;
    private String userImg;

    @Builder
    public ReCommentSaveRequestDto(
            String userId,
            Long userNumberId,
            String userImg,
            Long commentId,
            String comment,
            Long postId
    ) {
        this.userId = userId;
        this.userNumberId = userNumberId;
        this.userImg = userImg;
        this.commentId = commentId;
        this.postId = postId;
        this.comment = comment;
    }


    public ReComment toEntity() {
        return ReComment.builder()
                .userId(userId)
                .userNumberId(userNumberId)
                .userImg(userImg)
                .postId(postId)
                .commentId(commentId)
                .comment(comment)
                .build();

    }

}
