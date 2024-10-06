package com.example.YoungJu_Lee_Spring.Service;

import com.example.YoungJu_Lee_Spring.domain.Article;
import com.example.YoungJu_Lee_Spring.domain.Comment;
import com.example.YoungJu_Lee_Spring.dto.request.CommentCreateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.request.CommentUpdateRequestDto;
import com.example.YoungJu_Lee_Spring.dto.response.CommentResponseDto;
import com.example.YoungJu_Lee_Spring.repository.ArticleRepository;
import com.example.YoungJu_Lee_Spring.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public Long createComment(CommentCreateRequestDto requestDto) {
        Article article = articleRepository.findById(requestDto.getArticleId())
                .orElseThrow(() -> new EntityNotFoundException("Article not found."));

        Comment comment = Comment.builder()
                .article(article)
                .content(requestDto.getContent())
                .build();

        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public List<CommentResponseDto> findCommentsByArticleId(Long articleId){
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(comment.getId(), comment.getContent(), comment.getArticle().getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(requestDto.getCommentId())
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        comment.setContent(requestDto.getContent());

        return new CommentResponseDto(comment.getId(),comment.getContent(), comment.getArticle().getId());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
