package com.example.Social.Media.Service.ServiceImplements;

import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Entity.User;

import java.util.List;

public interface UserService {

    public UserDto registerUser(UserDto userDto);
    public UserDto findUserById(Integer userId);
    public UserDto findUserByEmail(String email);
    public UserDto followUser(Integer userId1, Integer userId2);
    public List<UserDto> findAllUser();
    public UserDto updateUser(UserDto userDto, Integer userId );
    public List<UserDto> searchUser(String query);
    public String deleteUserById(Integer userId);
    public String deleteAll();
}
