package com.application.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "SignIn Request Body")
@Data
public class SignInDTO {

    @ApiModelProperty(value = "User name / Email credential")
    private String userNameEmail;

    @ApiModelProperty(value = "Password to login")
    private String password;
}
