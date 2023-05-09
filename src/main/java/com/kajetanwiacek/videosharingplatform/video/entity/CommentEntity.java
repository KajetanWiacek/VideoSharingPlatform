package com.kajetanwiacek.videosharingplatform.video.entity;

import com.kajetanwiacek.videosharingplatform.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "comment")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private User userEntity;

  @ManyToOne private VideoEntity videoEntity;

  private LocalDateTime createdDate;

  private String content;
}
