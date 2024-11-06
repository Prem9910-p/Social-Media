package com.example.Social.Media.Service.ServiceImplements;

import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Entity.User;
import com.example.Social.Media.Exception.UserAlreadyExistsException;
import com.example.Social.Media.Mapper.UserMapper;
import com.example.Social.Media.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplements implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {

        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Email already exists : "+userDto.getEmail());
        }

        User user=UserMapper.mapToUser(userDto);

       User savedUser= userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto findUserById(Integer UserId) {
        return null;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto followUser(Integer userId1, Integer UserId2) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> searchUser(String query) {
        return List.of();
    }
}
