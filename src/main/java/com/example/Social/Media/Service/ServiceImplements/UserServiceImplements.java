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
    public UserDto findUserById(Integer userId) {

       Optional<User> optionalUser=userRepository.findById(userId);

       if(optionalUser.isEmpty()){
           throw new RuntimeException(" user is not present: "+ userId);
       }
       User user= optionalUser.get();
       return UserMapper.mapToUserDto(user);


    }

    @Override
    public UserDto findUserByEmail(String email) {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User is not present with email: "+email);
        }
        User user=optionalUser.get();

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto followUser(Integer userId1, Integer userId2) {
        UserDto userDto1=findUserById(userId1);
        UserDto userDto2=findUserById(userId2);

        userDto2.getFollowers().add(userDto1.getId());
        userDto1.getFollowings().add(userDto2.getId());

        User user=userRepository.save(UserMapper.mapToUser(userDto1));

        userRepository.save(UserMapper.mapToUser(userDto2));



        return UserMapper.mapToUserDto(user);
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
