package com.kajetanwiacek.videosharingplatform.exception;

public class UserEmailNotFoundException extends RuntimeException{
    public UserEmailNotFoundException(String email) {
        super("User with given email: "+email+" not found");
    }
}
