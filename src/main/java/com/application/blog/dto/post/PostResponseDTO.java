package com.application.blog.dto.post;

import com.application.blog.dto.comment.CommentResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(description = "Post Response Body")
public class PostResponseDTO {
    @ApiModelProperty(value = "Post Id")
    private Long id;

    @ApiModelProperty(value = "Title of the Post")
    private String title;

    @ApiModelProperty(value = "Post description")
    private String description;

    @ApiModelProperty(value = "Post Content Body")
    private String content;

    @ApiModelProperty(value = "Collection of comments for a post")
    private Set<CommentResponseDTO> comments;
}
