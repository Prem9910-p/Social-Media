package com.example.Social.Media.Dto;


import lombok.*;

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


}
