package com.example.Social.Media.Service.ServiceImplements;

import com.example.Social.Media.Dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createNewPost(PostDto postDto, Integer userId);

    String deletePost(Integer postId, Integer userId);

    List<PostDto> findPostByUserId(Integer userId);

    PostDto findPostByPostId(Integer postId);

    List<PostDto> findAllPost();

    PostDto savePost(Integer postId, Integer UserId);

    PostDto likePost(Integer postId, Integer UserId);
}
