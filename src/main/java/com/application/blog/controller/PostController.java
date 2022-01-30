package com.application.blog.controller;

import com.application.blog.dto.post.PaginationResponse;
import com.application.blog.dto.post.PostRequestDTO;
import com.application.blog.dto.post.PostResponseDTO;
import com.application.blog.service.PostService;
import com.application.blog.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
        log.info("Creating a Post...");
        return new ResponseEntity<>(postService.createPost(postRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public PaginationResponse getPosts(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                       @RequestParam(value = "orderBy", defaultValue = AppConstants.DEFAULT_ORDER_BY, required = false) String orderBy) {
        log.info("Returning all the Posts...");
        return postService.getAllPosts(pageNo, pageSize, sortBy, orderBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable String id) {
        log.info("Get a Post...");
        return new ResponseEntity<PostResponseDTO>(postService.getPost(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable String id, @RequestBody PostRequestDTO postRequestDTO) {
        log.info("Update a Post...");
        return new ResponseEntity<>(postService.updatePost(id, postRequestDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        log.info("Delete a Post");
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
