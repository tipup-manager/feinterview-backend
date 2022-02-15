package com.fei.api.service.recomment;

import com.fei.api.domain.comment.Comment;
import com.fei.api.domain.comment.CommentRepository;
import com.fei.api.domain.post.Post;
import com.fei.api.domain.post.PostRepository;
import com.fei.api.domain.recomment.ReComment;
import com.fei.api.domain.recomment.ReCommentRepository;
import com.fei.api.web.dto.comment.CommentListResponseDto;
import com.fei.api.web.dto.comment.CommentSaveRequestDto;
import com.fei.api.web.dto.recomment.ReCommentListResponseDto;
import com.fei.api.web.dto.recomment.ReCommentSaveRequestDto;
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
public class ReCommentService {

    private final CommentRepository commentRepository;
    private final ReCommentRepository reCommentRepository;


    @Transactional
    public ReCommentListResponseDto save(ReCommentSaveRequestDto reCommentSaveRequestDto) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        if (reCommentSaveRequestDto == null || "".equals(reCommentSaveRequestDto.getUserNumberId())) {
            throw new RuntimeException("Invalid argument");
        }
        Comment comment = commentRepository.findById(reCommentSaveRequestDto.getCommentId()).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다.id="+reCommentSaveRequestDto.getCommentId())
        );
        comment.updateRecommentCount();
        reCommentRepository.save(reCommentSaveRequestDto.toEntity());
        Page<ReComment> pageData = reCommentRepository.findByCommentId(reCommentSaveRequestDto.getCommentId(), sortedByModifiedDateDesc);
        return new ReCommentListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }


    @Transactional(readOnly = true)
    public ReCommentListResponseDto getReCommentByBlogId(Long commentId, Pageable pageable) {
        Page<ReComment> pageData = reCommentRepository.findByCommentId(commentId, pageable);
        return new ReCommentListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }


    @Transactional
    public ReCommentListResponseDto delete(Long id){
        ReComment recomment = reCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 답글이 없습니다. id="+id));
        long commentId = recomment.getCommentId();
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다.id="+commentId)
        );
        comment.updateRemoveRecommentCount();
        reCommentRepository.delete(recomment);
        Pageable sortedByModifiedDateDesc = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        Page<ReComment> pageData = reCommentRepository.findByCommentId(commentId, sortedByModifiedDateDesc);
        return new ReCommentListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }
}
