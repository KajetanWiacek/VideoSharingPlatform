package com.kajetanwiacek.videosharingplatform.video.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long videoId;
    private String username;
    private String content;

    public Comment(Long userId, Long videoId, String username, String content) {
        this.userId = userId;
        this.videoId = videoId;
        this.username = username;
        this.content = content;
    }
}
