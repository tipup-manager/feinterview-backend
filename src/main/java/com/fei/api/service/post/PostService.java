package com.fei.api.service.post;

import com.fei.api.domain.comment.Comment;
import com.fei.api.domain.comment.CommentRepository;
import com.fei.api.domain.post.Post;
import com.fei.api.domain.post.PostRepository;
import com.fei.api.domain.user.User;
import com.fei.api.domain.user.UserRepository;
import com.fei.api.web.dto.comment.CommentListResponseDto;
import com.fei.api.web.dto.post.PostListResponseDto;
import com.fei.api.web.dto.post.PostResponseDto;
import com.fei.api.web.dto.post.PostResponseWithoutUserDto;
import com.fei.api.web.dto.post.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public PostResponseDto save(PostSaveRequestDto postSaveRequestDto) {
        if (postSaveRequestDto == null || "".equals(postSaveRequestDto.getUserNumberId())) {
            throw new RuntimeException("Invalid argument");
        }
        Post post = postRepository.save(postSaveRequestDto.toEntity());
        return new PostResponseDto(post, null, null, null, null, null);
    }
    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 블로그가 없습니다.id="+id)
        );
        User user = userRepository.findById(post.getUserNumberId()).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다.id="+id)
        );
        Pageable sortedByModifiedDateDesc = PageRequest.of(0, 10, Sort.by("createdDate").descending());
        Page<Comment> pageData = commentRepository.findByPostId(id, sortedByModifiedDateDesc);
        return new PostResponseDto(
            post,
            user.getTitle(),
            user.getEmail(),
            user.getHomePageUrl(),
            user.getGithubUrl(),
            new CommentListResponseDto(
                    pageData.getContent(),
                    pageData.getTotalElements(),
                    pageData.getPageable().getPageNumber()
            )
        );
    }


    @Transactional(readOnly = true)
    public PostListResponseDto getPostByCategory(String category, Pageable pageable) {
        Page<Post> pageData = postRepository.findByCategory(category, pageable);
        return new PostListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public PostListResponseDto getPosts(Pageable pageable) {
        Page<Post> pageData = postRepository.findPostList(pageable);
        return new PostListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public PostListResponseDto getPostsByUserNumberId(Long userNumberId, Pageable pageable) {
        Page<Post> pageData = postRepository.findByUserNumberId(userNumberId, pageable);
        return new PostListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그가 없습니다. id="+id));

        postRepository.delete(post);
    }
}
