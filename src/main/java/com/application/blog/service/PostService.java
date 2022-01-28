package com.application.blog.service;

import com.application.blog.dto.post.PaginationResponse;
import com.application.blog.dto.post.PostRequestDTO;
import com.application.blog.dto.post.PostResponseDTO;

public interface PostService {
    PostResponseDTO createPost(PostRequestDTO postRequestDTO);
    PaginationResponse getAllPosts(int pageNo, int pageSize, String sortBy, String orderBy);
    PostResponseDTO getPost(String id);
    PostResponseDTO updatePost(String id, PostRequestDTO postRequestDTO);
    void deletePost(String id);
}
