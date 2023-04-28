package com.kajetanwiacek.videosharingplatform.video.model;

import com.kajetanwiacek.videosharingplatform.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "video")
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToOne private User user;

  private String quality;

  private DictionaryCategoryEntity dictionaryCategoryEntity;
}
