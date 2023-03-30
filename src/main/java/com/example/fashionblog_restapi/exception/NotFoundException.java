package com.example.fashionblog_restapi.exception;

public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException(){
        super("Not Found");
        this.message="Not Found";
    }
    public NotFoundException(String msg){
        super(msg);
        this.message=msg;
    }
}
