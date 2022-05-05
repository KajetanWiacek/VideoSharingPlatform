package com.kajetanwiacek.videosharingplatform.video.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long videoId;

    @ElementCollection
    private Set<Long> userIdLikes;

    public Stats(Long videoId) {
        this.videoId = videoId;
        userIdLikes = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return Objects.equals(id, stats.id) && Objects.equals(videoId, stats.videoId) && Objects.equals(userIdLikes, stats.userIdLikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, videoId, userIdLikes);
    }
}
