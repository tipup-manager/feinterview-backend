package com.fei.api.service.comment;

import com.fei.api.domain.comment.Comment;
import com.fei.api.domain.comment.CommentRepository;
import com.fei.api.domain.post.Post;
import com.fei.api.domain.post.PostRepository;
import com.fei.api.domain.recomment.ReCommentRepository;
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
    private final ReCommentRepository reCommentRepository;

    @Transactional
    public CommentListResponseDto save(CommentSaveRequestDto commentSaveRequestDto) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        if (commentSaveRequestDto == null || "".equals(commentSaveRequestDto.getUserNumberId())) {
            throw new RuntimeException("Invalid argument");
        }
        Post post = postRepository.findById(commentSaveRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("해당 포스트가 없습니다.id="+commentSaveRequestDto.getPostId())
        );
        String starCount = getStarCountFactory(post.getStarCountInfo(), commentSaveRequestDto.getScore());
        post.starUpdate(getStarAverage(starCount),starCount);
        commentRepository.save(commentSaveRequestDto.toEntity());
        Page<Comment> pageData = commentRepository.findByPostId(commentSaveRequestDto.getPostId(), sortedByModifiedDateDesc);
        return new CommentListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    public String getStarCountFactory(String starCount, int currentStar) {
        String tempCount = starCount;
        if (tempCount.indexOf("/") > -1) {
            String[] starArry = tempCount.split("/");
            int star = Integer.parseInt(starArry[0]) + currentStar;
            int people = Integer.parseInt(starArry[1]) + 1;
            tempCount = Integer.toString(star) + "/" + Integer.toString(people);
        }
        return tempCount;
    }

    public int getStarAverage(String starCount) {
        int resultAverage = 0;
        if (starCount.indexOf("/") > -1) {
            String[] starArry = starCount.split("/");
            int star = Integer.parseInt(starArry[0]);
            int people = Integer.parseInt(starArry[1]);
            resultAverage = Math.round(star/people);
        }
        return resultAverage;
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
    public CommentListResponseDto delete(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id="+id));
        long postId = comment.getPostId();
        commentRepository.delete(comment);
        reCommentRepository.deleteByCommentId(id);

        Pageable sortedByModifiedDateDesc = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        Page<Comment> pageData = commentRepository.findByPostId(postId, sortedByModifiedDateDesc);
        return new CommentListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }
}
