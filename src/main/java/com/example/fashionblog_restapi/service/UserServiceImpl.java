package com.example.fashionblog_restapi.service;
import com.example.fashionblog_restapi.dto.*;
import com.example.fashionblog_restapi.exception.NotFoundException;
import com.example.fashionblog_restapi.exception.PostAlreadyLikedException;
import com.example.fashionblog_restapi.model.Comment;
import com.example.fashionblog_restapi.model.Like;
import com.example.fashionblog_restapi.model.Post;
import com.example.fashionblog_restapi.model.User;
import com.example.fashionblog_restapi.repository.CommentRepository;
import com.example.fashionblog_restapi.repository.LikeRepository;
import com.example.fashionblog_restapi.repository.PostRepository;
import com.example.fashionblog_restapi.repository.UserRepository;
import com.example.fashionblog_restapi.response.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
@Getter
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;


    @Override
    public RegisterResponse register(UserDto userDTO) {
        Optional<User> existingUser = userRepository.findUserByEmail(userDTO.getEmail());
    if (existingUser.isPresent())
        throw new NotFoundException("user already exist");
    User user = new User();
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword());
    userRepository.save(user);

        return new RegisterResponse("Success", LocalDateTime.now(), user);
    }

    @Override
    public LoginResponse login(LoginDto loginDTO) {
        User user = findUserByEmail(loginDTO.getEmail());
        LoginResponse loginResponse =  null;
        if(user != null){
            if(user.getPassword().equals(loginDTO.getPassword())){
                loginResponse = new LoginResponse("success", LocalDateTime.now());
            }
            else {
                loginResponse = new LoginResponse("wrong Password", LocalDateTime.now());
            }

        } else {loginResponse = new LoginResponse("user not found", LocalDateTime.now());}

        return loginResponse;
    }



    @Override
    public CreatePostResponse createPost(PostDto postDTO) {
        Post post = new Post();
        User user = findUserById(postDTO.getUser_id());

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setUser(user);
        postRepository.save(post);
        return new CreatePostResponse("success post",LocalDateTime.now(),post);
    }

    @Override
    public CommentResponse comment(int user_id, int post_id, CommentDto commentDTO) {
        Comment comment = new Comment();
        Post post = findPostById(post_id);
        User user = findUserById(user_id);

        comment.setComment(commentDTO.getComment());
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return new CommentResponse("",comment,LocalDateTime.now(),post);
    }

    @Override
    public LikedResponse like(int user_id, int post_id, LikeDto likeDTO) {
        Like like = new Like();

        Post post = findPostById(post_id);
        User user = findUserById(user_id);
        LikedResponse likedResponse = null;

        Like duplicateLike = likeRepository.findLikedByUserIdAndPostId(user_id,post_id);
        if(duplicateLike == null){

            like.setLiked(likeDTO.isLiked());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            List<Like> likeList = likeRepository.likeList(post_id);
            likedResponse = new LikedResponse("Success",LocalDateTime.now(),like,likeList.size());
        }else{
            likeRepository.delete(duplicateLike);
            throw new PostAlreadyLikedException("This post has been liked");
        }
        return likedResponse;
    }

    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> commentList = commentRepository.findByCommentContainingIgnoreCase(keyword);
        return new SearchCommentResponse("Success",LocalDateTime.now(),commentList);
    }

    @Override
    public SearchPostResponse searchPost(String keyword) {
        List<Post>  postList = postRepository.findByTitleContainingIgnoreCase(keyword);
        return new SearchPostResponse("Success",LocalDateTime.now(),postList);
    }
    public Post findPostById(int id){
        return postRepository.findById(id).orElseThrow(()-> new NotFoundException("Post not found"));
    }
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()-> new NotFoundException("User with email: "+ email + " not found"));
    }
    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User with ID: " + id + " not found"));

    }

}
