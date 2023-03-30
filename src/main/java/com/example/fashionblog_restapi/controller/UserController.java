package com.example.fashionblog_restapi.controller;

import com.example.fashionblog_restapi.dto.*;
import com.example.fashionblog_restapi.response.*;
import com.example.fashionblog_restapi.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/blog")
@Slf4j
public class UserController {
    private final UserService userService;


    @PostMapping(value ="/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid UserDto userDto){
        log.info("Successfully Registered {} ", userDto.getEmail());
        return new ResponseEntity<>(userService.register(userDto), CREATED);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto){
        log.info("Welcome {} ", loginDto.getEmail());
        return new ResponseEntity<>(userService.login(loginDto), OK);

    }
    @PostMapping(value="/create")
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody PostDto postDto){
        log.info("Successfully Registered {} ", postDto.getTitle());
        return new ResponseEntity<>(userService.createPost(postDto), CREATED);
    }

    @PostMapping(value="/comment/{user_id}/{post_id}")
    public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value="post_id") Integer post_id, @RequestBody CommentDto commentDto){
        log.info("Successfully commented {} ", commentDto.getComment());
        return new ResponseEntity<>(userService.comment(user_id, post_id, commentDto), OK);

    }

    @PostMapping(value="/like/{user_id}/{post_id}")
    public ResponseEntity<LikedResponse> like(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value="post_id") Integer post_id, @RequestBody LikeDto likeDto){
        log.info("Successfully liked {} ", likeDto.isLiked());
        return new ResponseEntity<>(userService.like(user_id, post_id, likeDto), OK);
    }
}
