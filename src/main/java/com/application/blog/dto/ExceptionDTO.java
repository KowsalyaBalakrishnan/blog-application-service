package com.application.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ApiModel(description = "Exception Model Object")
public class ExceptionDTO {

    @ApiModelProperty(value = "Exception created timestamp")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "Exception message")
    private String message;

    @ApiModelProperty(value = "Exception details")
    private String details;
}
