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

    private List<Integer> followers = new ArrayList<>();


    private List<Integer> followings = new ArrayList<>();

}
