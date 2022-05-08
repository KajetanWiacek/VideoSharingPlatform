package com.kajetanwiacek.videosharingplatform.video.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(userId, comment.userId) && Objects.equals(videoId, comment.videoId) && Objects.equals(username, comment.username) && Objects.equals(content, comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, videoId, username, content);
    }
}
