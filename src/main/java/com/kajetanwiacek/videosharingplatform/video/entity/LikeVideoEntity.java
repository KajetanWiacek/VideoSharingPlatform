package com.kajetanwiacek.videosharingplatform.video.entity;

import com.kajetanwiacek.videosharingplatform.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "like_video_entity")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LikeVideoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private VideoEntity videoEntity;

  @OneToOne private User user;
}
