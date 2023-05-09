package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.exception.VideoNotFoundException;
import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.user.UserService;
import com.kajetanwiacek.videosharingplatform.video.dto.VideoListView;
import com.kajetanwiacek.videosharingplatform.video.dto.VideoUploadCommand;
import com.kajetanwiacek.videosharingplatform.video.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

  private final VideoRepository videoRepository;
  private final UserService userService;
  private final VideoMapper videoMapper;
  private final CommentRepository commentRepository;
  private final StatsRepository statsRepository;

  public List<VideoListView> getUserVideos(String email) {
    User user = userService.getUser(email);

    return videoRepository.getByUser(user);
  }
  public VideoEntity getVideo(Long id) {
    return videoRepository.findById(id).orElseThrow(VideoNotFoundException::new);
  }

  public void addVideo(String email, VideoUploadCommand videoUploadCommand) {
    User user = userService.getUser(email);
    VideoEntity videoEntity = videoMapper.map(videoUploadCommand, user);

    videoRepository.save(videoEntity);
    statsRepository.save(new LikeVideoEntity(videoEntity.getId()));
  }

  public List<VideoListView> getVideosFromCategory(Long categoryId) {
    return videoRepository.getByCategory(categoryId).stream()
        .map(info -> VideoListView.builder().build())
        .collect(Collectors.toList());
  }
}
