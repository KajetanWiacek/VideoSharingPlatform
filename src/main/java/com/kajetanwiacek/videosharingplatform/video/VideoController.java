package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.video.dto.VideoListView;
import com.kajetanwiacek.videosharingplatform.video.dto.VideoUploadCommand;
import com.kajetanwiacek.videosharingplatform.video.entity.CommentEntity;
import com.kajetanwiacek.videosharingplatform.video.entity.VideoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {
  private final VideoService videoService;

  @GetMapping("/my")
  public List<VideoListView> getUserVideos(Principal principal) {
    return videoService.getUserVideos(principal.getName());
  }

  @GetMapping
  public List<VideoEntity> getOtherUserVideos(@RequestParam String username) {
    return videoService.getOtherUserVideos(username);
  }

  @GetMapping("{id}")
  public VideoEntity getVideo(@PathVariable Long id) {
    return videoService.getVideo(id);
  }

  @GetMapping("{id}/comments")
  public List<CommentEntity> getComments(@PathVariable Long id) {
    return videoService.getVideoComments(id);
  }

  @PostMapping("{id}")
  public String commentVideo(
      @PathVariable Long id, @RequestBody String content, Principal principal) {
    videoService.commentVideo(id, content, principal.getName());
    return "Comment has been uploaded";
  }

  @PostMapping
  public String uploadVideo(
      Principal principal, @RequestBody VideoUploadCommand videoUploadCommand) {
    videoService.addVideo(principal.getName(), videoUploadCommand);
    return "Video has been uploaded";
  }

  @PostMapping("{id}/like")
  public String likeVideo(@PathVariable Long id, Principal principal) {
    return videoService.likeVideo(id, principal.getName());
  }

  @GetMapping("{id}/like")
  public Integer getLikesNumber(@PathVariable Long id) {
    return videoService.getLikes(id);
  }

  @GetMapping("/category/{categoryId}")
  public List<VideoEntity> getCategoryVideos(@PathVariable Long categoryId) {
    return videoService.getVideosFromCategory(categoryId);
  }
}
