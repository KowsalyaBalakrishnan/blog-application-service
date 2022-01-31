package com.application.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "JWT Authentication Response")
public class JwtAuthResponse {

    @ApiModelProperty(value = "JWT Access token generated when login/signin")
    private String accessToken;

    @ApiModelProperty(value = "Bearer JWT token type")
    private String tokenType = "Bearer ";

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
