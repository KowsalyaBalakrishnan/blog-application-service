package com.application.blog.service;

import com.application.blog.dto.comment.CommentRequestDTO;
import com.application.blog.dto.comment.CommentResponseDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CommentService {
    CommentResponseDTO createComment(String postId, CommentRequestDTO commentRequestDTO);
    List<CommentResponseDTO> getAllComments(String postId);
    CommentResponseDTO getComment(String postId, String commentId);
    CommentResponseDTO updateComment(String postId, String commentId, CommentRequestDTO commentRequestDTO);
    CommentResponseDTO partialUpdateComment(String postId, String commentId, CommentRequestDTO commentRequestDTO);
    HttpStatus deleteComment(String postId, String commentId);
}
