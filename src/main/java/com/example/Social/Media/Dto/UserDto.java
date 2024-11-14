package com.example.Social.Media.Dto;


import com.example.Social.Media.Entity.Post;
import com.example.Social.Media.Entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto  {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private List<Integer> followers = new ArrayList<>();


    private List<Integer> followings = new ArrayList<>();
    private List<Post> savedPost= new ArrayList<>();

}
