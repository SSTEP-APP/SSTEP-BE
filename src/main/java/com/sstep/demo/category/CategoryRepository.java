package com.sstep.demo.category;

import com.sstep.demo.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT s.categories FROM Store s WHERE s.id = :storeId ")
    List<Category> findCategoriesByStoreId(Long storeId);
}
