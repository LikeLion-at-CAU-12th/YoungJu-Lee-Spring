package com.example.YoungJu_Lee_Spring.repository;


import com.example.YoungJu_Lee_Spring.domain.Article;
import com.example.YoungJu_Lee_Spring.domain.CategoryArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryArticleRepository extends JpaRepository<CategoryArticle, Long> {
    List<CategoryArticle> findByArticle(Article article);
}