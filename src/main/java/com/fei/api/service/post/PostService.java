package com.fei.api.service.post;

import com.fei.api.domain.post.Post;
import com.fei.api.domain.post.PostRepository;
import com.fei.api.web.dto.post.PostListResponseDto;
import com.fei.api.web.dto.post.PostResponseDto;
import com.fei.api.web.dto.post.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto save(PostSaveRequestDto postSaveRequestDto) {
        if (postSaveRequestDto == null || "".equals(postSaveRequestDto.getUserNumberId())) {
            throw new RuntimeException("Invalid argument");
        }
        Post post = postRepository.save(postSaveRequestDto.toEntity());
        return new PostResponseDto(post);
    }
    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 블로그가 없습니다.id="+id)
        );
        return new PostResponseDto(post);
    }


    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostByCategory(String category) {
        return postRepository.findByCategoryOrderByCreatedDateDesc(category).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
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
