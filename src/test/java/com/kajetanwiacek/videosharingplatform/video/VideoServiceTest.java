package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.user.UserService;
import com.kajetanwiacek.videosharingplatform.video.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class VideoServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private VideoMapper videoMapper;

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private VideoService videoService;

    private User user;
    private VideoUploadDto uploadDto;
    private Video video;
    private Comment comment;

    @Before
    public void startUp(){
        user = new User(1L,"example@gmail.com","password","user");
        Length length = new Length(1, 10, 5);
        uploadDto = new VideoUploadDto("video", length,Quality.Q1080P,Category.ART);
        video = new Video(1L,"video",user, length,Quality.Q1080P,Category.ART);
        comment = new Comment(user.getId(),video.getId(),user.getUsername(),"content");
    }

    @Test
    public void addVideo_shouldAddVideo_validDataGiven(){
        when(userService.getUser(user.getEmail())).thenReturn(user);
        when(videoMapper.toEntity(uploadDto,user)).thenReturn(video);

        videoService.addVideo(user.getEmail(),uploadDto);

        ArgumentCaptor<Video> videoCaptor = ArgumentCaptor.forClass(Video.class);
        verify(videoRepository).save(videoCaptor.capture());
        Video savedVideo = videoCaptor.getValue();
        ArgumentCaptor<Stats> statsCaptor = ArgumentCaptor.forClass(Stats.class);
        Stats videoStats = new Stats(video.getId());
        verify(statsRepository).save(statsCaptor.capture());
        Stats savedStats = statsCaptor.getValue();

        Assert.assertEquals(video,savedVideo);
        Assert.assertEquals(videoStats,savedStats);
    }

    @Test
    public void likeVideo_shouldChangeLikesNumber_userLikedVideo(){
        when(userService.getUser(user.getEmail())).thenReturn(user);
        when(videoRepository.getById(video.getId())).thenReturn(video);
        Stats stats = new Stats(video.getId());
        when(statsRepository.findById(video.getId())).thenReturn(Optional.of(stats));

        videoService.likeVideo(video.getId(),user.getEmail());
        ArgumentCaptor<Stats> statsCaptor = ArgumentCaptor.forClass(Stats.class);
        verify(statsRepository).save(statsCaptor.capture());
        Stats savedStats = statsCaptor.getValue();

        Assert.assertEquals(1,savedStats.getUserIdLikes().size());
    }

    @Test
    public void commentVideo_shouldAddComment_userWroteComment(){
        when(userService.getUser(user.getEmail())).thenReturn(user);
        when(videoRepository.findById(video.getId())).thenReturn(Optional.of(video));

        videoService.commentVideo(video.getId(),"content",user.getEmail());
        ArgumentCaptor<Comment> argumentCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepository).save(argumentCaptor.capture());
        Comment savedComment = argumentCaptor.getValue();

        Assert.assertEquals(comment,savedComment);
    }
}
