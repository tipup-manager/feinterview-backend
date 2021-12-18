package com.fei.api.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
    Boolean existsByUserId(String userId);
    User findByUserIdAndPw(String userId, String pw);

    @Query(nativeQuery = true, value="SELECT * FROM user c")
    Page<User> findUserList(Pageable pageable);
}
