package com.kajetanwiacek.videosharingplatform.video;

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

    public List<Video> getVideos(){
        return videoRepository.findAll();
    }

    public List<Video> getUserVideos(String email){
        User user = userService.getUser(email);

        return videoRepository.getByUser(user);
    }

    public Video getVideo(Long id){
        return videoRepository.findById(id).get();
    }

    public void addVideo(String email, VideoUploadDto videoUploadDto){
        User user = userService.getUser(email);
        Video video = videoMapper.toEntity(videoUploadDto,user);

        videoRepository.save(video);
        statsRepository.save(new Stats(video.getId()));
    }

    public void commentVideo(Long videoId, String content, String email){
        User user = userService.getUser(email);
        Video video = videoRepository.getById(videoId);

        commentRepository.save(new Comment(user.getId(),video.getId(), user.getUsername(),content));
    }

    public List<Comment> getVideoComments(Long videoId){
        return commentRepository.getByVideoId(videoId);
    }

    public void likeVideo(Long videoId, String email){
        User user = userService.getUser(email);

        Stats stats = statsRepository.getById(videoId);
        if(stats.getUserIdLikes().contains(user.getId())){
            throw new IllegalStateException("zalikeowane juz");
        }
        statsRepository.getById(videoId).getUserIdLikes().add(user.getId());

        statsRepository.save(stats);
    }

    public Integer getLikes(Long videoId){
        Video video = videoRepository.getById(videoId);
        Stats stats = statsRepository.getById(videoId);

        return stats.getUserIdLikes().size();
    }

    public List<Video> getVideosFromCategory(Category category){
        return videoRepository.getByCategory(category);
    }
}
