package com.application.blog.dto.post;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PostRequestDTO {
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Post Title should have at least 5 characters")
    private String title;

    @NotEmpty
    @Size(min = 15, message = "Post description should have at least 15 characters")
    @Size(max = 25, message = "Maximum 25 characters are allowed")
    private String description;

    @NotEmpty
    @Size(min = 30, message = "Minimum 30 characters required for a Post content")
    private String content;
}
