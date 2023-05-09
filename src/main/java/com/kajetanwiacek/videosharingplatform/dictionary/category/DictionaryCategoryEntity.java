package com.kajetanwiacek.videosharingplatform.dictionary.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "dictionary_category")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DictionaryCategoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
}
