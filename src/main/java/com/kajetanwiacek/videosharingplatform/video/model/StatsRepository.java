package com.kajetanwiacek.videosharingplatform.video.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<LikeVideoEntity,Long> {
}
