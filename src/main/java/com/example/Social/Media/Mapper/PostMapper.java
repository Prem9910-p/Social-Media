package com.example.Social.Media.Mapper;

import com.example.Social.Media.Dto.PostDto;
import com.example.Social.Media.Entity.Post;

import java.time.LocalDateTime;

public class PostMapper {


    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .caption(post.getCaption())
                .image(post.getImage())
                .video(post.getVideo())
                .user(post.getUser())
                .createdAt(post.getCreatedAt())
                .liked(post.getLiked())
                .build();
    }
    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .caption(postDto.getCaption())
                .image(postDto.getImage())
                .video(postDto.getVideo())
                .user(postDto.getUser())
                .createdAt(postDto.getCreatedAt())
                .liked(postDto.getLiked())
                .build();
    }


}
