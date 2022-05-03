package com.kajetanwiacek.videosharingplatform.exception;

public class EmailAlreadyTakenException extends RuntimeException{
    public EmailAlreadyTakenException(String email){
        super("User with given email : "+email+" already exists");
    }
}
