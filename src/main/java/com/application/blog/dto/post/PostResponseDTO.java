package com.application.blog.dto.post;

import com.application.blog.dto.comment.CommentResponseDTO;
import com.application.blog.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentResponseDTO> comments;
}
