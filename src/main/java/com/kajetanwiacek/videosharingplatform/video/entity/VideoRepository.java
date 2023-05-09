package com.kajetanwiacek.videosharingplatform.video.entity;

import com.kajetanwiacek.videosharingplatform.user.User;
import com.kajetanwiacek.videosharingplatform.video.projection.VideoInfo;
import com.kajetanwiacek.videosharingplatform.video.projection.VideoListInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
  List<VideoEntity> getByUser(User user);

  List<VideoListInfo> getByCategory(Long categoryId);

  boolean existsById(Long id);
}
