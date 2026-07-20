package com.productivitytracker.tracker.controller;
import com.productivitytracker.tracker.dto.UserDto;
import com.productivitytracker.tracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")

public class UserController {
private UserService userService;

//build add user restapi
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser , HttpStatus.CREATED);
    }
    //Build get user REST api
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
       UserDto userDto= userService.getUserById(userId);
       return ResponseEntity.ok(userDto);
    }
    //Build get all user REST api
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> users=userService.getAllUser();
        return ResponseEntity.ok(users);
    }
    //Build update user REST api
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser( @PathVariable("id") Long UserId ,@RequestBody UserDto updateUser){
        UserDto userDto= userService.updateUser(UserId , updateUser);
     return ResponseEntity.ok(userDto);
    }

    //build delete user REST api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted sucessfully!");
    }
}

