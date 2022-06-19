package com.fei.api.domain.language;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query("SELECT c FROM Language c WHERE c.userNumberId = ?1 ORDER BY c.id DESC")
    List<Language> findByUserNumberId(Long userNumberId);

    Long deleteByUserNumberId(Long userNumberId);


}
