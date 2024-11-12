package com.example.Social.Media.Entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;

    @OneToMany
    private List<User> liked= new ArrayList<>();
}
