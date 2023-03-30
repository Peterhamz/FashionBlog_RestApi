package com.example.fashionblog_restapi.response;


import com.example.fashionblog_restapi.model.Like;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikedResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Like like;
    private int totalLikes;

}
