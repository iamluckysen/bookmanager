package com.project.bookmanager.service;

import com.project.bookmanager.dto.UserLoginDto;
import com.project.bookmanager.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(User user);
    String login(UserLoginDto userLoginDto);
}
