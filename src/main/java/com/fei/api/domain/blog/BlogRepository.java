package com.fei.api.domain.blog;

import com.fei.api.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
    Boolean existsByUserId(String userId);
    User findByOauthId(String oauthId);

    @Query(nativeQuery = true, value="SELECT * FROM user c")
    Page<User> findUserList(Pageable pageable);
}
