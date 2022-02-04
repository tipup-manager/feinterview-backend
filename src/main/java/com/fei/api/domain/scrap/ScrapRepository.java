package com.fei.api.domain.scrap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM scrap c")
    Page<Scrap> findScrapList(Pageable pageable);

    Page<Scrap> findByUserNumberId(Long userNumberId, Pageable pageable);

    Scrap findByUserNumberIdAndBlogId(Long userNumberId, Long blogId);
}
