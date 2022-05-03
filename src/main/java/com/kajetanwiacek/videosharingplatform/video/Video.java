package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    private Integer likes;

    @ElementCollection
    private List<Comment> comments;

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
