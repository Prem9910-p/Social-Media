package com.example.Social.Media.Controller;

import com.example.Social.Media.Dto.PostDto;
import com.example.Social.Media.Service.ServiceImplements.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Post")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/add/{userId}")
    public ResponseEntity createNewPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId){
       try {
           PostDto postDto1 = postService.createNewPost(postDto, userId);
           return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }


}
