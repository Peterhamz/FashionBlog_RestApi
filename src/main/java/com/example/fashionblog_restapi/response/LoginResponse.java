package com.example.fashionblog_restapi.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String Message;
    private LocalDateTime timeStamp;
}
