package com.kajetanwiacek.videosharingplatform.video.model;

import com.kajetanwiacek.videosharingplatform.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User user;

    @Embedded
    private Length length;

    private Quality quality;
    private Category category;

    public Video(String name, Length length, Quality quality, Category category, User user) {
        this.name = name;
        this.length = length;
        this.quality = quality;
        this.category = category;
        this.user = user;
    }
}
