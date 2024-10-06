package com.example.YoungJu_Lee_Spring.Service;

import com.example.YoungJu_Lee_Spring.domain.Article;
import com.example.YoungJu_Lee_Spring.domain.Category;
import com.example.YoungJu_Lee_Spring.domain.ArticleLog;
import com.example.YoungJu_Lee_Spring.domain.CategoryArticle;
import com.example.YoungJu_Lee_Spring.domain.Member;
import com.example.YoungJu_Lee_Spring.dto.request.ArticleCreateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.response.ArticleResponseDto;
import com.example.YoungJu_Lee_Spring.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    // 의존성 주입
    @Autowired
    private MemberJpaRepository memberRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryArticleRepository categoryArticleRepository;
    @Autowired
    private ArticleLogRepository articleLogRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    // 클라이언트에서 dto에 맞는 형식의 데이터를 주면 그걸로 인스턴스 생성
    public Long createArticle(ArticleCreateRequestDto requestDto) {
        //멤버가 존재하는지 확인
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(()-> new RuntimeException("해당 아이디를 가진 회원이 존재하지 않습니다."));
        //존재하면 article 인스턴스 생성
        Article article = Article.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .comments(new ArrayList<>())
                .build();
        articleRepository.save(article);

        ArticleLog articleLog = ArticleLog.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .article(article)
                .build();
        articleLogRepository.save(articleLog);

        List<Long> categoryIds = requestDto.getCategoryIds();
        if(categoryIds != null && !categoryIds.isEmpty()) {
            for(Long categoryId : categoryIds) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(()-> new RuntimeException("해당 ID를 가진 카테고리가 존재하지 않습니다."));

                CategoryArticle categoryArticle = CategoryArticle.builder()
                        .category(category)
                        .article(article)
                        .build();

                categoryArticleRepository.save(categoryArticle);
            }
        }
    
        //생성된 article 인스턴스 반환
        return article.getId();
    }

    public List<ArticleResponseDto> findArticlesByMemberId(Long memberId) {
        List<Article> articles = articleRepository.findByMemberId(memberId);
        return articles.stream()
                .map(article -> new ArticleResponseDto(article.getId(), article.getTitle(), article.getContent()))
                .collect(Collectors.toList());
    }
}
