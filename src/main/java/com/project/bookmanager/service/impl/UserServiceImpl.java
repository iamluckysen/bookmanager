package com.project.bookmanager.service.impl;

import com.project.bookmanager.dto.UserLoginDto;
import com.project.bookmanager.dto.UserRegistrationDto;
import com.project.bookmanager.entity.User;
import com.project.bookmanager.exceptions.UserAlreadyExistsException;
import com.project.bookmanager.repo.UserRepository;
import com.project.bookmanager.security.JwtUtil;
import com.project.bookmanager.service.UserService;
import  com.project.bookmanager.exceptions.BadCredentialException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());

       if(!userRepository.existsByEmail(user.getEmail())){
           String hashedPassword = passwordEncoder.encode(user.getPassword());
           user.setPassword(hashedPassword);
          return userRepository.save(user);
       }
       else {
           throw new UserAlreadyExistsException("User already exists with email " + user.getEmail());
       }


    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(()-> new BadCredentialException("Invalid email or password"));

        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            throw new BadCredentialException("Invalid email or password");
        }
        return jwtUtil.generateToken(userLoginDto.getEmail());
    }


}
