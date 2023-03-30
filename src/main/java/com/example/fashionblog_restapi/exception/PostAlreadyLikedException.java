package com.example.fashionblog_restapi.exception;

public class PostAlreadyLikedException extends RuntimeException{

    private String message;

    public PostAlreadyLikedException(){

        super("Already Liked");

        this.message="Already Liked";
    }

    public PostAlreadyLikedException(String msg){
        super(msg);
        this.message=msg;
    }
}
