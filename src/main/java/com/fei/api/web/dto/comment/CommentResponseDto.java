package com.fei.api.web.dto.comment;

import com.fei.api.domain.comment.Comment;
import com.fei.api.domain.post.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private final Long id;
    private String userId;
    private Long userNumberId;
    private Long postId;
    private String comment;
    private String userImg;
    private int score;
    private int recommentCount;


    public CommentResponseDto(Comment comment) {
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.userNumberId = comment.getUserNumberId();
        this.userImg = comment.getUserImg();
        this.score = comment.getScore();
        this.postId = comment.getPostId();
        this.comment = comment.getComment();
        this.recommentCount = comment.getRecommentCount();
    }
}
