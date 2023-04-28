package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.video.model.VideoEntity;
import com.kajetanwiacek.videosharingplatform.video.model.VideoUploadDto;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    public VideoEntity toEntity(VideoUploadDto videoDto, User user){
        Length.validate(videoDto.getLength());
        return new VideoEntity(videoDto.getName(),videoDto.getLength(),videoDto.getQuality(),videoDto.getCategory(), user);
    }
}
