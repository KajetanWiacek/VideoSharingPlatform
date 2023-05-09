package com.kajetanwiacek.videosharingplatform.dictionary.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryCategoryRepository
    extends JpaRepository<DictionaryCategoryEntity, Long> {
    List<DictionaryCategoryDto> getAll();
}
