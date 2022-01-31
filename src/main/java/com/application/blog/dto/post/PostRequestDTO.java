package com.application.blog.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel(description = "Post Request Body")
public class PostRequestDTO {

    @ApiModelProperty(value = "Post Id")
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Post Title should have at least 5 characters")
    @ApiModelProperty(value = "Title of the Post")
    private String title;

    @NotEmpty
    @Size(min = 15, message = "Post description should have at least 15 characters")
    @Size(max = 25, message = "Maximum 25 characters are allowed")
    @ApiModelProperty(value = "Post description")
    private String description;

    @NotEmpty
    @Size(min = 30, message = "Minimum 30 characters required for a Post content")
    @ApiModelProperty(value = "Post Content Body")
    private String content;
}
