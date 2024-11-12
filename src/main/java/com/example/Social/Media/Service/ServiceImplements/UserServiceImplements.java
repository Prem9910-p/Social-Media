package com.example.Social.Media.Service.ServiceImplements;

import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Entity.User;
import com.example.Social.Media.Exception.UserAlreadyExistsException;
import com.example.Social.Media.Mapper.UserMapper;
import com.example.Social.Media.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<UserDto> findAllUser() {
       List<User> users= userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        UserDto userDto1=findUserById(userId);

        userDto1.setFirstName(userDto.getFirstName());
        userDto1.setLastName(userDto.getLastName());

        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Email already exists : "+userDto.getEmail());
        }


        userDto1.setEmail(userDto.getEmail());
        userDto1.setFollowings(userDto.getFollowings());
        userDto1.setFollowers(userDto.getFollowers());
        User user=UserMapper.mapToUser(userDto1);
           User savedUser=userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }


    @Override
    public List<UserDto> searchUser(String query) {
        List<User> userList=userRepository.searchUser(query);

        return userList.stream().map((user) -> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public String deleteUserById(Integer userId) {
            userRepository.deleteById(userId);
        return "user deleted";
    }

    @Override
    public String deleteAll() {
        userRepository.deleteAll();
        return "Deleted All user";
    }
}
