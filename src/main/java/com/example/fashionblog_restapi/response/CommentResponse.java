package com.example.fashionblog_restapi.response;
import com.example.fashionblog_restapi.model.Comment;
import com.example.fashionblog_restapi.model.Post;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentResponse {
   private String message;
   private Comment comment;
   private LocalDateTime timeStamp;
   private Post post;

}
