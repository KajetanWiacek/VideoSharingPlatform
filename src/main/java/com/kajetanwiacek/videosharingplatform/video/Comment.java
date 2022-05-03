package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @OneToOne
    private User user;

    private String content;
}
