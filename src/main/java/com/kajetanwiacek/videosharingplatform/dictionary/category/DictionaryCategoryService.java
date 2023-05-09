package com.kajetanwiacek.videosharingplatform.dictionary.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DictionaryCategoryService {
  private DictionaryCategoryRepository dictionaryCategoryRepository;

  List<DictionaryCategoryView> getAllCategories() {
    return dictionaryCategoryRepository.getAll().stream()
        .map(dto -> DictionaryCategoryView.builder().id(dto.getId()).name(dto.getName()).build())
        .collect(Collectors.toList());
  }
}
