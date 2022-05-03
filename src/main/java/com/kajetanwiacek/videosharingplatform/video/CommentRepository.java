package com.kajetanwiacek.videosharingplatform.video;

import com.kajetanwiacek.videosharingplatform.video.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getByVideoId(Long id);
}
