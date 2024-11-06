package com.example.Social.Media.Controller;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import com.example.Social.Media.Dto.UserDto;
import com.example.Social.Media.Exception.UserAlreadyExistsException;
import com.example.Social.Media.Service.ServiceImplements.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
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


}
