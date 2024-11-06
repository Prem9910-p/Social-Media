package com.example.Social.Media.Mapper;

import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .followers(user.getFollowers())
                .followings(user.getFollowings())
                .build();
    }

    public static User mapToUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .followers(userDto.getFollowers())
                .followings(userDto.getFollowings())
                .build();
    }
}
