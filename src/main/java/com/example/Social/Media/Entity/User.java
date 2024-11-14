package com.example.Social.Media.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;


    private List<Integer> followers= new ArrayList<>();


    private List<Integer> followings= new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<Post> savedPost= new ArrayList<>();


}
