package com.application.blog.dto.post;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponse {
    private List<PostResponseDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
