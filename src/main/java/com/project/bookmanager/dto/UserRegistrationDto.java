package com.project.bookmanager.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, message = "password must be at least 8 character long")
    private String password;

}
