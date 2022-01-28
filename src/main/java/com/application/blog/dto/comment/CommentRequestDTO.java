package com.application.blog.dto.comment;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentRequestDTO {
    private Long id;

    @NotEmpty(message = "Empty Name")
    @Size(min = 5, message = "Minimum character should be 5")
    private String name;

    @NotEmpty(message = "Empty email")
    @Email(message = "Invalid mail address")
    private String emailId;

    @NotEmpty(message = "Empty comment")
    private String commentBody;
}
