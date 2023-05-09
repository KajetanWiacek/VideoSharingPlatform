package com.kajetanwiacek.videosharingplatform.video.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VideoListView {
  String name;
  Long thumbnailId;
  Long userId;
  Long length;
}
