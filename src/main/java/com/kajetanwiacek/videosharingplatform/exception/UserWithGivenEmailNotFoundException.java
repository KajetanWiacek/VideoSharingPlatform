package com.kajetanwiacek.videosharingplatform.exception;

public class UserWithGivenEmailNotFoundException extends RuntimeException{
    public UserWithGivenEmailNotFoundException(String email) {
        super("User with given email: "+email+" not found");
    }
}
