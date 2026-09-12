package com.project.bookmanager.controller;


import com.project.bookmanager.dto.JwtAuthResponse;
import com.project.bookmanager.dto.UserLoginDto;
import com.project.bookmanager.dto.UserRegistrationDto;
import com.project.bookmanager.entity.User;
import com.project.bookmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private  final UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {

        return new ResponseEntity<>(userService.registerUser(userRegistrationDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        String token = userService.login(userLoginDto);
        return  ResponseEntity.ok(new JwtAuthResponse(token));
    }

}
