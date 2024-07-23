package com.cinema.backend.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meta {
  private int number;
  private int size;
  private long totalElements;
  private int totalPages;
  private boolean last;
  private boolean first;
}
