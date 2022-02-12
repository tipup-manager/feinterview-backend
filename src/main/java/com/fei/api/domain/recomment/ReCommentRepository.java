package com.fei.api.domain.recomment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReCommentRepository extends JpaRepository<ReComment, Long> {

    Page<ReComment> findByCommentId(Long commentId, Pageable pageable);

}
