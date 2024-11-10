package com.example.YoungJu_Lee_Spring.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArticleUpdateRequestDto {
    @NotEmpty // DTO 유효성 검사
    private Long memberId;
    @NotEmpty // DTO 유효성 검사
    private Long articleId;
    @NotEmpty // DTO 유효성 검사
    private String title;
    private String content;
    private List<Long> categoryIds;
}
