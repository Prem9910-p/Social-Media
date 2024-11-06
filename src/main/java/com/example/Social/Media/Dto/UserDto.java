package com.example.Social.Media.Dto;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private List<Integer> followers= new ArrayList<>();

//    private List<UserDto> followers = new ArrayList<>();  // Direct initialization

    // Other fields and methods
    public List<Integer> getFollowers() {
        return followers;
    }

//    public void setFollowers(List<UserDto> followers) {
//        this.followers = followers;
//    }

    private List<Integer> followings= new ArrayList<>();

}
