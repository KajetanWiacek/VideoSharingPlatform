package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    public Video toEntity(VideoUploadDto videoDto, User user){
        return new Video(videoDto.getName(),videoDto.getLength(),videoDto.getQuality(),videoDto.getCategory(), user);
    }
}
