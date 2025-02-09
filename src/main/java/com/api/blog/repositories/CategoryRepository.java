package com.api.blog.repositories;

import com.api.blog.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(
            "SELECT c FROM Category c LEFT JOIN FETCH c.posts"
    )
    List<Category> findAllWithPostCount();

}
