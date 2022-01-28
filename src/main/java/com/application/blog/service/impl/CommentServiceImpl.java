package com.application.blog.service.impl;

import com.application.blog.dto.comment.CommentRequestDTO;
import com.application.blog.dto.comment.CommentResponseDTO;
import com.application.blog.entity.Comment;
import com.application.blog.entity.Post;
import com.application.blog.exception.ResourceNotFoundException;
import com.application.blog.repository.CommentRepository;
import com.application.blog.repository.PostRespository;
import com.application.blog.service.CommentService;
import com.application.blog.utils.TypeConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRespository postRespository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentResponseDTO createComment(String postId, CommentRequestDTO commentRequestDTO) {
        Post post = postRespository.findById(Long.parseLong(postId)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = TypeConversion.mapToCommentEntity(commentRequestDTO);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return TypeConversion.mapToCommentDTO(savedComment);
    }

    @Override
    public List<CommentResponseDTO> getAllComments(String postId) {
        List<Comment> allById = commentRepository.getAllCommentsForPost(postId);
        List<CommentResponseDTO> commentResponseDTOList = allById.stream().map(TypeConversion::mapToCommentDTO).collect(Collectors.toList());
        return commentResponseDTOList;
    }

    @Override
    public CommentResponseDTO getComment(String postId, String commentId) {
        Comment comment = commentRepository.getByCommentIdAndPostId(postId, commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment", "id", commentId);
        }
        return TypeConversion.mapToCommentDTO(comment);
    }

    @Override
    public CommentResponseDTO updateComment(String postId, String commentId, CommentRequestDTO commentRequestDTO) {
        Comment commentDbData = commentRepository.getByCommentIdAndPostId(postId, commentId);
        if (commentDbData == null) {
            throw new ResourceNotFoundException("Comment", "id", commentId);
        }
        commentDbData.setName(commentRequestDTO.getName());
        commentDbData.setEmailId(commentRequestDTO.getEmailId());
        commentDbData.setCommentBody(commentRequestDTO.getCommentBody());

        Comment updatedComment = commentRepository.save(commentDbData);
        return TypeConversion.mapToCommentDTO(updatedComment);
    }

    @Override
    public CommentResponseDTO partialUpdateComment(String postId, String commentId, CommentRequestDTO commentRequestDTO) {
        Comment commentDbData = commentRepository.getByCommentIdAndPostId(postId, commentId);
        if (commentRequestDTO.getName() != null && !commentRequestDTO.getName().equals(commentDbData.getName())) {
            commentDbData.setName(commentRequestDTO.getName());
        }
        if (commentRequestDTO.getEmailId() != null && !commentDbData.getEmailId().equals(commentRequestDTO.getEmailId())) {
            commentDbData.setEmailId(commentRequestDTO.getEmailId());
        }
        if (commentRequestDTO.getCommentBody() != null && !commentDbData.getCommentBody().equals(commentRequestDTO.getCommentBody())) {
            commentDbData.setCommentBody(commentRequestDTO.getCommentBody());
        }
        Comment partialUpdatedComment = commentRepository.save(commentDbData);
        return TypeConversion.mapToCommentDTO(partialUpdatedComment);
    }

    @Override
    public HttpStatus deleteComment(String postId, String commentId) {
        Comment comment = commentRepository.getByCommentIdAndPostId(postId, commentId);
        if (comment != null) {
            commentRepository.deleteCommentByPostIdAndCommentId(postId, commentId);
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.NOT_FOUND;
    }
}
