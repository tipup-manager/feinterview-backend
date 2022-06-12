package com.fei.api.domain.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM category c")
    Page<Category> findCategoryList(Pageable pageable);

}
