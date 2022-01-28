package com.application.blog.dto.comment;

import com.application.blog.dto.post.PostResponseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class CommentResponseDTO {
    private long id;
    private String name;
    private String emailId;
    private String comment;
}
