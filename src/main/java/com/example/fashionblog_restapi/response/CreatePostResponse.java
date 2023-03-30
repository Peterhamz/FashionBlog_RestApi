package com.example.fashionblog_restapi.response;

import com.example.fashionblog_restapi.model.Post;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePostResponse {
    private String title;
    private LocalDateTime timeStamp;
    private Post post;
}
