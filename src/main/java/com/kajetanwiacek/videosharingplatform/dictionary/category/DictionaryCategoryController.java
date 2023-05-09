package com.kajetanwiacek.videosharingplatform.dictionary.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dictionaries/categories")
@RequiredArgsConstructor
public class DictionaryCategoryController {

  private DictionaryCategoryService dictionaryCategoryService;

  @GetMapping
  public List<DictionaryCategoryView> getCategories() {
    return dictionaryCategoryService.getAllCategories();
  }
}
