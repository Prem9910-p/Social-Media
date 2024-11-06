package com.example.Social.Media.Service.ServiceImplements;

import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Entity.User;

import java.util.List;

public interface UserService {

    public UserDto registerUser(UserDto userDto);
    public UserDto findUserById(Integer UserId);
    public UserDto findUserByEmail(String email);
    public UserDto followUser(Integer userId1, Integer UserId2);
    public UserDto updateUser(UserDto userDto);
    public List<UserDto> searchUser(String query);
}
