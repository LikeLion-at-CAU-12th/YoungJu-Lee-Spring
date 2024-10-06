package com.example.YoungJu_Lee_Spring.controller;

import com.example.YoungJu_Lee_Spring.Service.ArticleService;
import com.example.YoungJu_Lee_Spring.Service.CommentService;
import com.example.YoungJu_Lee_Spring.dto.request.ArticleCreateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.request.ArticleUpdateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.request.CommentCreateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.request.CommentUpdateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.response.ArticleResponseDto;
import com.example.YoungJu_Lee_Spring.dto.response.CommentResponseDto;
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

    @Autowired
    private CommentService commentService;
    
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
    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponseDto> updateArticle(@PathVariable Long articleId, @RequestBody ArticleUpdateRequestDto requestDto){
        requestDto.setArticleId(articleId);
        ArticleResponseDto article = articleService.updateArticle(requestDto);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId){
        articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<Long> createComment(@PathVariable Long articleId, @RequestBody CommentCreateRequestDto requestDto){
        requestDto.setArticleId(articleId);
        Long commentId = commentService.createComment(requestDto);
        return new ResponseEntity<>(commentId, HttpStatus.CREATED);
    }

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByArticleId(@PathVariable Long articleId){
        List<CommentResponseDto> comments = commentService.findCommentsByArticleId(articleId);
        if(comments.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{articleId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long articleId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto requestDto){
        requestDto.setArticleId(articleId);
        requestDto.setCommentId(commentId);
        CommentResponseDto comment = commentService.updateComment(requestDto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{articleId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long articleId, @PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
    
}

