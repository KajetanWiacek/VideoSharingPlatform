package com.kajetanwiacek.videosharingplatform.exception;

public class InvalidVideoLengthPatternException extends RuntimeException{
    public InvalidVideoLengthPatternException(String message) {
        super("Invalid length pattern: "+message+" out of range");
    }
}
