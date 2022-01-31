package com.application.blog.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ApiModel(description = "Comment Model Request Body")
public class CommentRequestDTO {

    @ApiModelProperty(value = "Comment ID")
    private Long id;

    @NotEmpty(message = "Empty Name")
    @Size(min = 5, message = "Minimum character should be 5")
    @ApiModelProperty(value = "Comment Name")
    private String name;

    @NotEmpty(message = "Empty email")
    @Email(message = "Invalid mail address")
    @ApiModelProperty(value = "Comment EmailID")
    private String emailId;

    @NotEmpty(message = "Empty comment")
    @ApiModelProperty(value = "Comment Body")
    private String commentBody;
}
