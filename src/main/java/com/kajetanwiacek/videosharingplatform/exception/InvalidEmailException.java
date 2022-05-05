package com.kajetanwiacek.videosharingplatform.exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {
        super("Invalid email pattern");
    }
}
