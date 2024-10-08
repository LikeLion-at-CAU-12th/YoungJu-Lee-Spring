package com.example.YoungJu_Lee_Spring.controller;

import com.example.YoungJu_Lee_Spring.Service.ArticleService;
import com.example.YoungJu_Lee_Spring.dto.request.ArticleCreateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.response.ArticleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    
    @PostMapping("")
    // 프론트에게 이러한 BODY로 넣어달라는 뜻
    public ResponseEntity<Long> createArticle(@RequestBody ArticleCreateRequestDto requestDto) {
        Long articleId = articleService.createArticle(requestDto);
        return new ResponseEntity<>(articleId, HttpStatus.CREATED);
    }
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ArticleResponseDto>> getArticleByMemberId(@PathVariable Long memberId){
        List<ArticleResponseDto> articles = articleService.findArticlesByMemberId(memberId);
        if(articles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }
    
}

