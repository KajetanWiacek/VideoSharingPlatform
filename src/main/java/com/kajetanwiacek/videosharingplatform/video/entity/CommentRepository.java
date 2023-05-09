package com.kajetanwiacek.videosharingplatform.video.entity;

import com.kajetanwiacek.videosharingplatform.video.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> getByVideoId(Long id);
}