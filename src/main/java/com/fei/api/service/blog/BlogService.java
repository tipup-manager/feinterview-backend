package com.fei.api.service.blog;

import com.fei.api.domain.blog.Blog;
import com.fei.api.domain.blog.BlogRepository;
import com.fei.api.domain.user.User;
import com.fei.api.web.dto.blog.BlogListResponseDto;
import com.fei.api.web.dto.blog.BlogResponseDto;
import com.fei.api.web.dto.blog.BlogSaveRequestDto;
import com.fei.api.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public BlogResponseDto save(BlogSaveRequestDto blogSaveRequestDto) {
        if (blogSaveRequestDto == null || "".equals(blogSaveRequestDto.getUserNumberId())) {
            throw new RuntimeException("Invalid argument");
        }
        Blog blog = blogRepository.save(blogSaveRequestDto.toEntity());
        return new BlogResponseDto(blog);
    }
    @Transactional
    public BlogResponseDto getBlog(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 블로그가 없습니다.id="+id)
        );
        return new BlogResponseDto(blog);
    }

    @Transactional
    public BlogListResponseDto getBlogs(Pageable pageable) {
        Page<Blog> pageData = blogRepository.findBlogList(pageable);
        return new BlogListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public BlogListResponseDto getBlogsByUserNumberId(Long userNumberId, Pageable pageable) {
        Page<Blog> pageData = blogRepository.findByUserNumberId(userNumberId, pageable);
        return new BlogListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public void delete(Long id){
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 블로그가 없습니다. id="+id));

        blogRepository.delete(blog);
    }
}
