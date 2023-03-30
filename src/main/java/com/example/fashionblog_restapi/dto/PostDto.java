package com.example.fashionblog_restapi.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String title;
    private String description;
    private int user_id;
}
