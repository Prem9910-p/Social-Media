package com.example.Social.Media.Service.ServiceImplements;

import com.example.Social.Media.Dto.PostDto;
import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Entity.Post;
import com.example.Social.Media.Entity.User;
import com.example.Social.Media.Mapper.PostMapper;
import com.example.Social.Media.Mapper.UserMapper;
import com.example.Social.Media.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImplement implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;


    @Override
    public PostDto createNewPost(PostDto postDto, Integer userId) {
        UserDto userDto =userService.findUserById(userId);

        Post post= PostMapper.mapToPost(postDto);
        Post newPost = new Post();
        newPost.setId(post.getId());
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(UserMapper.mapToUser(userDto));


        Post savedPost=postRepository.save(newPost);
        return PostMapper.mapToPostDto(savedPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
        Optional<Post> post=postRepository.findById(postId);
        UserDto userDto =userService.findUserById(userId);

        if(post.get().getUser().getId()!=UserMapper.mapToUser(userDto).getId()){
            throw new RuntimeException("User is not present for "+userId);
        }

        postRepository.deleteById(postId);

        return "Post is deleted";
    }

    @Override
    public List<PostDto> findPostByUserId(Integer userId) {
        List<Post> posts=postRepository.findPostByUserId(userId);

        return posts.stream().map((post -> PostMapper.mapToPostDto(post))).collect(Collectors.toList());
    }

    @Override
    public PostDto findPostByPostId(Integer postId) {
        Optional<Post> optionalPost=postRepository.findById(postId);

        if(optionalPost.isPresent()){
            throw new RuntimeException("Post is not present for "+postId);
        }
        Post post=optionalPost.get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> findAllPost() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map((post -> PostMapper.mapToPostDto(post))).collect(Collectors.toList());
    }

    @Override
    public PostDto savePost(Integer postId, Integer UserId) {
        return null;
    }

    @Override
    public PostDto likePost(Integer postId, Integer UserId) {

        Optional<Post> post=postRepository.findById(postId);
        UserDto userDto =userService.findUserById(UserId);
         User user=UserMapper.mapToUser(userDto);

        if(post.get().getLiked().contains(user)){
            post.get().getLiked().remove(user);
        }else {
            post.get().getLiked().add(new User());
        }
        Post post1=post.get();

        return PostMapper.mapToPostDto(post1);
    }
}
