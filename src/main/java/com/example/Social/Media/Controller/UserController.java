package com.example.Social.Media.Controller;

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


}
