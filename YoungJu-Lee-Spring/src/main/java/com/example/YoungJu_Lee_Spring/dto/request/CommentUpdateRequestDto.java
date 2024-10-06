package com.example.YoungJu_Lee_Spring.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private Long commentId;
    private Long articleId;
    private String content;
}
