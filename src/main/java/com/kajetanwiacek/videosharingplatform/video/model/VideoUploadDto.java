package com.kajetanwiacek.videosharingplatform.video.model;

import com.kajetanwiacek.videosharingplatform.video.model.Category;
import com.kajetanwiacek.videosharingplatform.video.model.Length;
import com.kajetanwiacek.videosharingplatform.video.model.Quality;
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
