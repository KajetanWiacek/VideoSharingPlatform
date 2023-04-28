package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.exception.VideoNotFoundException;
import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.user.UserService;
import com.kajetanwiacek.videosharingplatform.video.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final UserService userService;
    private final VideoMapper videoMapper;
    private final CommentRepository commentRepository;
    private final StatsRepository statsRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository,UserService userService, VideoMapper videoMapper,
                        CommentRepository commentRepository, StatsRepository statsRepository) {
        this.videoRepository = videoRepository;
        this.userService = userService;
        this.videoMapper = videoMapper;
        this.commentRepository = commentRepository;
        this.statsRepository = statsRepository;
    }

    public List<VideoEntity> getVideos(){
        return videoRepository.findAll();
    }

    public List<VideoEntity> getUserVideos(String email){
        User user = userService.getUser(email);

        return videoRepository.getByUser(user);
    }

    public List<VideoEntity> getOtherUserVideos(String username){
        User user = userService.getUserByUsername(username);

        return videoRepository.getByUser(user);
    }

    public VideoEntity getVideo(Long id){
        return videoRepository.findById(id).orElseThrow(VideoNotFoundException::new);
    }

    public void addVideo(String email, VideoUploadDto videoUploadDto){
        User user = userService.getUser(email);
        VideoEntity videoEntity = videoMapper.toEntity(videoUploadDto,user);

        videoRepository.save(videoEntity);
        statsRepository.save(new LikeVideoEntity(videoEntity.getId()));
    }

    public void commentVideo(Long videoId, String content, String email){
        User user = userService.getUser(email);
        VideoEntity videoEntity = getVideo(videoId);

        commentRepository.save(new CommentEntity(user.getId(), videoEntity.getId(), user.getUsername(),content));
    }

    public List<CommentEntity> getVideoComments(Long videoId){
        if(!videoRepository.existsById(videoId)){
            throw new VideoNotFoundException();
        }
        return commentRepository.getByVideoId(videoId);
    }

    public String likeVideo(Long videoId, String email){
        User user = userService.getUser(email);

        LikeVideoEntity likeVideoEntity = statsRepository.findById(videoId).orElseThrow(VideoNotFoundException::new);

        if(likeVideoEntity.getUserIdLikes().contains(user.getId())){
            likeVideoEntity.getUserIdLikes().remove(user.getId());
            statsRepository.save(likeVideoEntity);

            return "Video unliked";
        }
        likeVideoEntity.getUserIdLikes().add(user.getId());
        statsRepository.save(likeVideoEntity);

        return "Video liked";
    }

    public Integer getLikes(Long videoId){
        LikeVideoEntity likeVideoEntity = statsRepository.findById(videoId).orElseThrow(VideoNotFoundException::new);

        return likeVideoEntity.getUserIdLikes().size();
    }

    public List<VideoEntity> getVideosFromCategory(Category category){
        return videoRepository.getByCategory(category);
    }
}
