package com.fei.api.domain.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM blog c")
    Page<Blog> findBlogList(Pageable pageable);

    Page<Blog> findByUserNumberId(Long userNumberId, Pageable pageable);
}
