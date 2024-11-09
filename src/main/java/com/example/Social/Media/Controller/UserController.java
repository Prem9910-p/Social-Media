package com.example.Social.Media.Controller;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Exception.UserAlreadyExistsException;
import com.example.Social.Media.Service.ServiceImplements.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        try {
            UserDto userDto1 = userService.registerUser(userDto);
            return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity findUserById(@PathVariable("id") Integer userId){
         try {
             UserDto userDto = userService.findUserById(userId);
             return new ResponseEntity<>(userDto, HttpStatus.CREATED);
         } catch (Exception e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity findUserByEmail(@PathVariable("email") String email){
        try{
            UserDto userDto=userService.findUserByEmail(email);
            return new ResponseEntity<>(userDto,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/follow/{userid1}/{userid2}")
    public ResponseEntity followerUser(@PathVariable("userid1") Integer userId1, @PathVariable("userid2") Integer userId2){
        try{
        UserDto userDto= userService.followUser(userId1, userId2);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping
    public List<UserDto> findAllUsers(){
        List<UserDto> userDtos=userService.findAllUser();
        return userDtos;
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
       try{
            UserDto userDto1 = userService.updateUser(userDto, userId);
            return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
        }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable("userId") Integer userId){
        return userService.deleteUserById(userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        return userService.deleteAll();
    }

    @GetMapping("/search")
public List<UserDto> searchUser(@Param("query") String query){
        List<UserDto> userDtos=userService.searchUser(query);
        return userDtos;

    }
}
