package com.example.fashionblog_restapi.service;

import com.example.fashionblog_restapi.dto.*;
import com.example.fashionblog_restapi.response.*;

public interface UserService {

        RegisterResponse register(UserDto userDTO);

        LoginResponse login(LoginDto loginDTO);

        CreatePostResponse createPost(PostDto postDTO);

        CommentResponse comment(int user_id, int post_id, CommentDto commentDTO);

        LikedResponse like(int user_id, int post_id, LikeDto likeDTO);

        SearchCommentResponse searchComment(String keyword);

        SearchPostResponse searchPost(String keyword);

    }
