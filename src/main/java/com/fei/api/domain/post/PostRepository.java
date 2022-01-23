package com.fei.api.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM post c")
    Page<Post> findPostList(Pageable pageable);

    Page<Post> findByUserNumberId(Long userNumberId, Pageable pageable);

    List<Post> findByCategoryOrderByCreatedDateDesc(String category);
}
