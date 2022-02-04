package com.fei.api.service.comment;

import com.fei.api.domain.comment.Comment;
import com.fei.api.domain.comment.CommentRepository;
import com.fei.api.domain.post.Post;
import com.fei.api.domain.post.PostRepository;
import com.fei.api.web.dto.comment.CommentListResponseDto;
import com.fei.api.web.dto.comment.CommentResponseDto;
import com.fei.api.web.dto.comment.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentListResponseDto save(CommentSaveRequestDto commentSaveRequestDto) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        if (commentSaveRequestDto == null || "".equals(commentSaveRequestDto.getUserNumberId())) {
            throw new RuntimeException("Invalid argument");
        }
        Post post = postRepository.findById(commentSaveRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("해당 포스트가 없습니다.id="+commentSaveRequestDto.getPostId())
        );
        post.starUpdate(0,0,"");
        commentRepository.save(commentSaveRequestDto.toEntity());
        Page<Comment> pageData = commentRepository.findByPostId(commentSaveRequestDto.getPostId(), sortedByModifiedDateDesc);
        return new CommentListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }


    @Transactional(readOnly = true)
    public CommentListResponseDto getCommentByBlogId(Long postId, Pageable pageable) {
        Page<Comment> pageData = commentRepository.findByPostId(postId, pageable);
        return new CommentListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }


    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그가 없습니다. id="+id));

        commentRepository.delete(comment);
    }
}
