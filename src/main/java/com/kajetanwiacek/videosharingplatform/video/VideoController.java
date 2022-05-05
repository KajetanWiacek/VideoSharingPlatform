package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.UserService;
import com.kajetanwiacek.videosharingplatform.video.model.Category;
import com.kajetanwiacek.videosharingplatform.video.model.Comment;
import com.kajetanwiacek.videosharingplatform.video.model.Video;
import com.kajetanwiacek.videosharingplatform.video.model.VideoUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/my")
    public ResponseEntity<List<Video>> getUserVideos(Principal principal){
        return new ResponseEntity<>(videoService.getUserVideos(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Video>> getAllVideos(){
        return new ResponseEntity<>(videoService.getVideos(),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getOtherUserVideos(@RequestParam String username){
        return new ResponseEntity<>(videoService.getOtherUserVideos(username),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Video> getVideo(@PathVariable Long id){
        return new ResponseEntity<>(videoService.getVideo(id),HttpStatus.OK);
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long id){
        return new ResponseEntity<>(videoService.getVideoComments(id),HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> commentVideo(@PathVariable Long id, @RequestBody String content,Principal principal){
        videoService.commentVideo(id,content,principal.getName());
        return new ResponseEntity<>("Comment has been uploaded",HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> uploadVideo(Principal principal, @RequestBody VideoUploadDto videoUploadDto){
        videoService.addVideo(principal.getName(),videoUploadDto);
        return new ResponseEntity<>("Video has been uploaded",HttpStatus.OK);
    }

    @PostMapping("{id}/like")
    public ResponseEntity<String> likeVideo(@PathVariable Long id, Principal principal){
        String answer = videoService.likeVideo(id,principal.getName());
        return new ResponseEntity<>(answer,HttpStatus.OK);
    }

    @GetMapping("{id}/like")
    public ResponseEntity<Integer> getLikesNumber(@PathVariable Long id){
        return new ResponseEntity<>(videoService.getLikes(id),HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Video>> getCategoryVideos(@RequestParam Category category){
        return new ResponseEntity<>(videoService.getVideosFromCategory(category),HttpStatus.OK);
    }
}
