package com.kajetanwiacek.videosharingplatform.video.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoUploadDto {
    private String name;
    private Length length;
    private Quality quality;
    private Category category;
}
