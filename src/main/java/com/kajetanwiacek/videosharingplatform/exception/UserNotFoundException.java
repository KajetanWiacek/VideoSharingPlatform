package com.kajetanwiacek.videosharingplatform.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("User with given username: "+email+" not found");
    }
}
