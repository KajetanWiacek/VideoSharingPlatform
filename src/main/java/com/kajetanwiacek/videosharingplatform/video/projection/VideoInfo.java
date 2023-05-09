package com.kajetanwiacek.videosharingplatform.video.projection;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class VideoInfo {
  String name;

  String description;

  Long thumbnailId;

  Long categoryId;

  Long userId;

  Long lengthId;

  Integer likes;

  Long views;
}
