package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.video.model.Length;
import com.kajetanwiacek.videosharingplatform.video.model.Video;
import com.kajetanwiacek.videosharingplatform.video.model.VideoUploadDto;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    public Video toEntity(VideoUploadDto videoDto, User user){
        Length.validate(videoDto.getLength());
        return new Video(videoDto.getName(),videoDto.getLength(),videoDto.getQuality(),videoDto.getCategory(), user);
    }
}
