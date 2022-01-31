package com.application.blog.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Comment Model Response Body")
public class CommentResponseDTO {
    @ApiModelProperty(value = "Comment ID")
    private long id;

    @ApiModelProperty(value = "Comment name")
    private String name;

    @ApiModelProperty(value = "Comment EmailId")
    private String emailId;

    @ApiModelProperty(value = "Comment Body")
    private String comment;
}
