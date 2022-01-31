package com.application.blog.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Pagination Response Model")
public class PaginationResponse {

    @ApiModelProperty(value = "Collection of Posts content")
    private List<PostResponseDTO> content;

    @ApiModelProperty(value = "Page Number requested")
    private int pageNo;

    @ApiModelProperty(value = "Page Size requested")
    private int pageSize;

    @ApiModelProperty(value = "Total number of data")
    private long totalElements;

    @ApiModelProperty(value = "Total pages available")
    private int totalPages;

    @ApiModelProperty(value = "Check if the current page is the last")
    private boolean last;
}
