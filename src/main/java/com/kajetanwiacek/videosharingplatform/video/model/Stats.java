package com.kajetanwiacek.videosharingplatform.video.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

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
    private List<Long> userIdLikes;

    public Stats(Long videoId) {
        this.videoId = videoId;
    }
}
