package com.example.YoungJu_Lee_Spring.repository;

import com.example.YoungJu_Lee_Spring.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByMemberId(Long memberId);
}




