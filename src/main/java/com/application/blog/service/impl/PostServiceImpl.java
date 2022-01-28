package com.application.blog.service.impl;

import com.application.blog.dto.post.PaginationResponse;
import com.application.blog.dto.post.PostRequestDTO;
import com.application.blog.dto.post.PostResponseDTO;
import com.application.blog.entity.Post;
import com.application.blog.exception.ResourceNotFoundException;
import com.application.blog.repository.PostRespository;
import com.application.blog.service.PostService;
import com.application.blog.utils.TypeConversion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRespository postRespository;

    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        Post entity = TypeConversion.mapToPostEntity(postRequestDTO);
        Post savedPostData = postRespository.save(entity);
        return TypeConversion.mapToPostDTO(savedPostData);
    }

    @Override
    public PaginationResponse getAllPosts(int pageNo, int pageSize, String sortBy, String orderBy) {

        //Sorting order
        Sort sort = orderBy.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        //Implement pagination
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postPage = postRespository.findAll(pageable);
        List<PostResponseDTO> list = postPage.getContent().stream().map(TypeConversion::mapToPostDTO).collect(Collectors.toList());

        PaginationResponse response = new PaginationResponse();
        response.setContent(list);
        response.setPageNo(postPage.getNumber());
        response.setPageSize(postPage.getSize());
        response.setTotalElements(postPage.getTotalElements());
        response.setTotalPages(postPage.getTotalPages());
        response.setLast(postPage.isLast());
        return response;
    }

    @Override
    public PostResponseDTO getPost(String id) {
        Post post = postRespository.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return TypeConversion.mapToPostDTO(post);
    }

    @Override
    public PostResponseDTO updatePost(String id, PostRequestDTO postRequestDTO) {
        Post postData = postRespository.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postData.setContent(postRequestDTO.getContent());
        postData.setDescription(postRequestDTO.getDescription());
        postData.setTitle(postRequestDTO.getTitle());
        return TypeConversion.mapToPostDTO(postRespository.save(postData));
    }

    @Override
    public void deletePost(String id) {
        try {
            postRespository.deleteById(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
