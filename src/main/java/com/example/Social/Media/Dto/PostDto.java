package com.example.Social.Media.Dto;

import com.example.Social.Media.Entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {
    private int id;

    private String caption;

    private String image;

    private String video;

    private User user;

    private LocalDateTime createdAt;
    private List<User> liked= new ArrayList<>();
}
