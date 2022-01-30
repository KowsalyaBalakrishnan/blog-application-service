package com.application.blog.controller;

import com.application.blog.dto.comment.CommentRequestDTO;
import com.application.blog.dto.comment.CommentResponseDTO;
import com.application.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable("postId") String postId,
                                                            @Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        return new ResponseEntity<>(commentService.createComment(postId, commentRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getAllComments(@PathVariable("postId") String postId) {
        return new ResponseEntity<>(commentService.getAllComments(postId), HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> getComment(@PathVariable("postId") String postId,
                                                               @PathVariable("commentId") String commentId) {
        CommentResponseDTO responseDTO = commentService.getComment(postId, commentId);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO , HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTO , HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId,
                                                            @Valid @RequestBody CommentRequestDTO commentRequestDTO) {

        CommentResponseDTO responseDTO = commentService.updateComment(postId, commentId, commentRequestDTO);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO , HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTO , HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> partialUpdateComment(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId,
                                                            @Valid @RequestBody CommentRequestDTO commentRequestDTO) {

        CommentResponseDTO responseDTO = commentService.partialUpdateComment(postId, commentId, commentRequestDTO);
        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO , HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTO , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deletecomment(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId) {
        HttpStatus status = commentService.deleteComment(postId, commentId);
        ResponseEntity<String> response;
        if (status.is2xxSuccessful()) {
            response = new ResponseEntity<>("Deletion successful", status);
            return response;
        }
        return new ResponseEntity<>("Data not found", status);
    }

}
