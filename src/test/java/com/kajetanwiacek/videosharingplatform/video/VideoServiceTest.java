package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.user.UserService;
import com.kajetanwiacek.videosharingplatform.video.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

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

    @InjectMocks
    private VideoService videoService;

    @Test
    public void addVideo_shouldAddVideo_validDataGiven(){
        User user = new User(1L,"example@gmail.com","password","user");
        when(userService.getUser(user.getEmail())).thenReturn(user);
        Length length = new Length(1,10,5);
        VideoUploadDto uploadDto = new VideoUploadDto("video",length, Quality.Q1080P, Category.ART);
        Long videoId = 1L;
        Video video = new Video(videoId,"video",user,length,Quality.Q1080P,Category.ART);
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
}
