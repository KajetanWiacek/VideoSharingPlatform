package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.video.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> getByVideoId(Long id);
}
