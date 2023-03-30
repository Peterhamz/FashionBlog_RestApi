package com.example.fashionblog_restapi.response;

import com.example.fashionblog_restapi.model.User;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private String message;
    private LocalDateTime timeStamp;
    private User user;
}
