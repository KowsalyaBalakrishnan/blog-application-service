package com.application.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionDTO {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
