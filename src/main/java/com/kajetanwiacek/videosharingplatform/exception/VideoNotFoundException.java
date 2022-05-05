package com.kajetanwiacek.videosharingplatform.exception;

public class VideoNotFoundException extends RuntimeException{
    public VideoNotFoundException() {
        super("Video with given id not found");
    }
}
