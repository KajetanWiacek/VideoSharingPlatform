package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.user.UserService;
import com.kajetanwiacek.videosharingplatform.video.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class VideoEntityServiceTest {

  @MockBean private UserService userService;

  @MockBean private VideoRepository videoRepository;

  @MockBean private VideoMapper videoMapper;

  @MockBean private StatsRepository statsRepository;

  @MockBean private CommentRepository commentRepository;

  VideoService videoService;

  private User user;
  private VideoUploadDto uploadDto;
  private VideoEntity videoEntity;
  private CommentEntity commentEntity;

  @BeforeEach
  public void startUp() {
    videoService =
        new VideoService(
            videoRepository, userService, videoMapper, commentRepository, statsRepository);
    user = new User(1L, "example@gmail.com", "password", "user");
    Length length = new Length(1, 10, 5);
    uploadDto = new VideoUploadDto("video", length, Quality.Q1080P, Category.ART);
    videoEntity = new VideoEntity(1L, "video", user, length, Quality.Q1080P, Category.ART);
    commentEntity = new CommentEntity(user.getId(), videoEntity.getId(), user.getUsername(), "content");
  }

  @Test
  void addVideo_shouldAddVideo_validDataGiven() {
    when(userService.getUser(user.getEmail())).thenReturn(user);
    when(videoMapper.toEntity(uploadDto, user)).thenReturn(videoEntity);

    videoService.addVideo(user.getEmail(), uploadDto);

    ArgumentCaptor<VideoEntity> videoCaptor = ArgumentCaptor.forClass(VideoEntity.class);
    verify(videoRepository).save(videoCaptor.capture());
    VideoEntity savedVideoEntity = videoCaptor.getValue();
    ArgumentCaptor<LikeVideoEntity> statsCaptor = ArgumentCaptor.forClass(LikeVideoEntity.class);
    LikeVideoEntity videoLikeVideoEntity = new LikeVideoEntity(videoEntity.getId());
    verify(statsRepository).save(statsCaptor.capture());
    LikeVideoEntity savedLikeVideoEntity = statsCaptor.getValue();

    Assertions.assertEquals(videoEntity, savedVideoEntity);
    Assertions.assertEquals(videoLikeVideoEntity, savedLikeVideoEntity);
  }

  @Test
  public void likeVideo_shouldChangeLikesNumber_userLikedVideo() {
    when(userService.getUser(user.getEmail())).thenReturn(user);
    when(videoRepository.getById(videoEntity.getId())).thenReturn(videoEntity);
    LikeVideoEntity likeVideoEntity = new LikeVideoEntity(videoEntity.getId());
    when(statsRepository.findById(videoEntity.getId())).thenReturn(Optional.of(likeVideoEntity));

    videoService.likeVideo(videoEntity.getId(), user.getEmail());
    ArgumentCaptor<LikeVideoEntity> statsCaptor = ArgumentCaptor.forClass(LikeVideoEntity.class);
    verify(statsRepository).save(statsCaptor.capture());
    LikeVideoEntity savedLikeVideoEntity = statsCaptor.getValue();

    Assertions.assertEquals(1, savedLikeVideoEntity.getUserIdLikes().size());
  }

  @Test
  public void commentVideo_shouldAddComment_userWroteComment() {
    when(userService.getUser(user.getEmail())).thenReturn(user);
    when(videoRepository.findById(videoEntity.getId())).thenReturn(Optional.of(videoEntity));

    videoService.commentVideo(videoEntity.getId(), "content", user.getEmail());
    ArgumentCaptor<CommentEntity> argumentCaptor = ArgumentCaptor.forClass(CommentEntity.class);
    verify(commentRepository).save(argumentCaptor.capture());
    CommentEntity savedCommentEntity = argumentCaptor.getValue();

    Assertions.assertEquals(commentEntity, savedCommentEntity);
  }
}
