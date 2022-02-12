package com.fei.api.web.dto.recomment;

import com.fei.api.domain.recomment.ReComment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReCommentResponseDto {

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private final Long id;
    private String userId;
    private Long userNumberId;
    private Long postId;
    private String comment;
    private String userImg;
    private Long commentId;


    public ReCommentResponseDto(ReComment recomment) {
        this.createdDate = recomment.getCreatedDate();
        this.modifiedDate = recomment.getModifiedDate();
        this.id = recomment.getId();
        this.userId = recomment.getUserId();
        this.userNumberId = recomment.getUserNumberId();
        this.userImg = recomment.getUserImg();
        this.commentId = recomment.getCommentId();
        this.postId = recomment.getPostId();
        this.comment = recomment.getComment();
    }
}
