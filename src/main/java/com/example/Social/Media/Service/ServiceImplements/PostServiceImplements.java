package com.example.Social.Media.Service.ServiceImplements;

import com.example.Social.Media.Dto.PostDto;
import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Entity.Post;
import com.example.Social.Media.Entity.User;
import com.example.Social.Media.Mapper.PostMapper;
import com.example.Social.Media.Mapper.UserMapper;
import com.example.Social.Media.Repository.PostRepository;
import com.example.Social.Media.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImplements implements PostService{
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public PostDto createNewPost(PostDto postDto, Integer userId) {
             UserDto userDto= userService.findUserById(userId);
             postDto.setCreatedAt(LocalDateTime.now());
             postDto.setUser(UserMapper.mapToUser(userDto));
       Post post= postRepository.save(PostMapper.mapToPost(postDto));
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
        Optional<Post> post=postRepository.findById(postId);

        if(post.isEmpty()){
            throw new RuntimeException("Post is not present for this id: "+postId);
        }
        if(post.get().getUser().getId()!=userId){
            throw new RuntimeException("User is not exist for this id :"+userId);
        }
        postRepository.deleteById(postId);
        return " post deleted successfully";
    }

    @Override
    public List<PostDto> findPostByUserId(Integer userId) {
        List<Post> postList=postRepository.findPostByUserId(userId);

        return postList.stream().map((post -> PostMapper.mapToPostDto(post))).collect(Collectors.toList());
    }

    @Override
    public PostDto findPostByPostId(Integer postId) {
        Optional<Post> post=postRepository.findById(postId);
        if (post.isEmpty()){
            throw new RuntimeException("Post is not present for ths id: "+postId);
        }
               Post post1=post.get();
        return PostMapper.mapToPostDto(post1);
    }

    @Override
    public List<PostDto> findAllPost() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map((post -> PostMapper.mapToPostDto(post))).collect(Collectors.toList());
    }

    @Override
    public PostDto savePost(Integer postId, Integer UserId) {
        PostDto postDto=findPostByPostId(postId);
        Post post=PostMapper.mapToPost(postDto);

        UserDto userDto=userService.findUserById(UserId);
        User user=UserMapper.mapToUser(userDto);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);

        return PostMapper.mapToPostDto(post);
    }

    @Override
    public PostDto likePost(Integer postId, Integer UserId) {
         PostDto postDto=findPostByPostId(postId);
         Post post=PostMapper.mapToPost(postDto);

         UserDto userDto=userService.findUserById(UserId);
        User user=UserMapper.mapToUser(userDto);

        if(post.getLiked().contains(user)){
             post.getLiked().remove(user);
         }else {
             post.getLiked().add(user);
         }
        Post savedPost=postRepository.save(post);
        return PostMapper.mapToPostDto(savedPost);
    }
}
