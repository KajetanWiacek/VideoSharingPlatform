package com.kajetanwiacek.videosharingplatform.video.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VideoView {
  String name;
  String description;
  Long thumbnailId;
  Long categoryId;
  Long userId;
  Long length;
  Integer likes;
  Long views;
}
