package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.user.UserRepository;
import com.kajetanwiacek.videosharingplatform.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserService userService;
    private final VideoMapper videoMapper;

    @Autowired
    public VideoService(VideoRepository videoRepository,UserService userService, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.userService = userService;
        this.videoMapper = videoMapper;
    }

    public List<Video> getVideos(){
        return videoRepository.findAll();
    }

    public List<Video> getUserVideos(String email){
        User user = userService.getUser(email);

        return videoRepository.getByUser(user);
    }

    public void addVideo(String email,VideoUploadDto videoUploadDto){
        User user = userService.getUser(email);
        Video video = videoMapper.toEntity(videoUploadDto,user);

        videoRepository.save(video);
    }
}
