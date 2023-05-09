package com.kajetanwiacek.videosharingplatform.video.projection;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class VideoListInfo {
  String name;

  Long thumbnailId;

  Long userId;

  Long length;
}
