package com.application.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "SignUp / Registration Request Body")
public class SignUpDTO {

    @ApiModelProperty(value = "Name of the user")
    private String name;

    @ApiModelProperty(value = "UserName required to login")
    private String userName;

    @ApiModelProperty(value = "Email required to login")
    private String emailId;

    @ApiModelProperty(value = "Password required to login")
    private String password;
}
