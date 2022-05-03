package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final UserService userService;
    private final VideoService videoService;

    @Autowired
    public VideoController(UserService userService,VideoService videoService) {
        this.userService = userService;
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
    public ResponseEntity<List<Video>> getOtherUserVideos(@RequestParam String userEmail){
        return new ResponseEntity<>(videoService.getUserVideos(userEmail),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> uploadVideo(Principal principal, @RequestBody VideoUploadDto videoUploadDto){
        videoService.addVideo(principal.getName(),videoUploadDto);
        return new ResponseEntity<>("Video has been uploaded",HttpStatus.OK);
    }
}
