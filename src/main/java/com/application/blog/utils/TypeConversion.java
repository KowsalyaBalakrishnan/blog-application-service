package com.application.blog.utils;

import com.application.blog.dto.comment.CommentRequestDTO;
import com.application.blog.dto.comment.CommentResponseDTO;
import com.application.blog.dto.post.PostRequestDTO;
import com.application.blog.dto.post.PostResponseDTO;
import com.application.blog.entity.Comment;
import com.application.blog.entity.Post;
import org.modelmapper.ModelMapper;

public class TypeConversion {

    /*We should Autowire model mapper as we configured Bean in main class.
    But since the method is static, we created object*/

    // Post Entity Conversions
    public static Post mapToPostEntity(PostRequestDTO postRequestDTO) {
        /*Post entity = new Post();
        entity.setTitle(postRequestDTO.getTitle());
        entity.setDescription(postRequestDTO.getDescription());
        entity.setContent(postRequestDTO.getContent());*/
        Post post = new ModelMapper().map(postRequestDTO, Post.class);
        return post;
    }

    public static PostResponseDTO mapToPostDTO(Post post) {
        /*PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(post.getId());
        postResponseDTO.setTitle(post.getTitle());
        postResponseDTO.setDescription(post.getDescription());
        postResponseDTO.setContent(post.getContent());*/
        PostResponseDTO postResponseDTO = new ModelMapper().map(post, PostResponseDTO.class);
        return postResponseDTO;
    }

    // Comment Entity Conversions
    public static Comment mapToCommentEntity(CommentRequestDTO commentRequestDTO) {
        /*Comment entity = new Comment();
        entity.setEmailId(commentRequestDTO.getEmailId());
        entity.setName(commentRequestDTO.getName());
        entity.setCommentBody(commentRequestDTO.getComment());*/
        Comment comment = new ModelMapper().map(commentRequestDTO, Comment.class);
        return comment;
    }

    public static CommentResponseDTO mapToCommentDTO(Comment comment) {
        /*CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setName(comment.getName());
        commentResponseDTO.setEmailId(comment.getEmailId());
        commentResponseDTO.setComment(comment.getCommentBody());*/
        CommentResponseDTO commentResponseDTO = new ModelMapper().map(comment, CommentResponseDTO.class);
        return commentResponseDTO;
    }
}
