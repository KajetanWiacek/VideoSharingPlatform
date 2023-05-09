package com.kajetanwiacek.videosharingplatform.dictionary.category;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DictionaryCategoryView {
  Long id;
  String name;
}
