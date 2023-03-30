package com.example.fashionblog_restapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Name should not be empty")
    private String name;
    @Email(message = "invalid email")
    private String email;
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Invalid password entered")
    private String password;
}
