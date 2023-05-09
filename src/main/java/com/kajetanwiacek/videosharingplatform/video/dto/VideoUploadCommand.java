package com.kajetanwiacek.videosharingplatform.video.dto;

import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VideoUploadCommand {
  String name;
  Long dictionaryCategoryId;
}
