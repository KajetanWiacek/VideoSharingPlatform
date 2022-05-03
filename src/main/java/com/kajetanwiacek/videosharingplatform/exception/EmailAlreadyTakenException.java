package com.kajetanwiacek.videosharingplatform.exception;

public class UsernameAlreadyTakenException extends RuntimeException{
    public UsernameAlreadyTakenException(String email){
        super("User with given username : "+email+" already exists");
    }
}
