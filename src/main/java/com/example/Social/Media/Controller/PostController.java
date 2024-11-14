package com.example.Social.Media.Controller;

import com.example.Social.Media.Dto.PostDto;
import com.example.Social.Media.Service.ServiceImplements.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/add/{userId}")
    public ResponseEntity CreateNewPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId){
       try {
           PostDto postDto1 = postService.createNewPost(postDto, userId);
           return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
       }

       @DeleteMapping("/post/{postId}/user/{userId}")
    public ResponseEntity deletePost(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId ){
         try {
             String confirm= postService.deletePost(postId, userId);
             return new ResponseEntity<>(confirm, HttpStatus.CREATED);
         } catch (Exception e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
       }

       @GetMapping("postList/user/{userId}")
    public List<PostDto> findPostByUserId(@PathVariable("userId") Integer userId){
     List<PostDto> postDtos=postService.findPostByUserId(userId);
     return postDtos;
    }

    @GetMapping("/findPost/{postId}")
    public ResponseEntity findPostByPostId(@PathVariable("postId") Integer postId){
        try {
            PostDto postDto=postService.findPostByPostId(postId);
            return  new ResponseEntity<>(postDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allPost")
    public List<PostDto> findAllPost(){
        return postService.findAllPost();
    }

    @PutMapping("/like/post/{postId}/user/{userId}")
    public ResponseEntity likePost(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId ){
        try {
            PostDto postDto= postService.likePost(postId, userId);
            return new ResponseEntity<>(postDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/save/post/{postId}/user/{userId}")
    public ResponseEntity savePost(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId ){
        try {
            PostDto postDto= postService.savePost(postId, userId);
            return new ResponseEntity<>(postDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
