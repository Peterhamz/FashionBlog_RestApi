package com.example.fashionblog_restapi.pojos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private String message;
    private int status;
    private T data;
}
