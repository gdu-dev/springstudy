package com.gdu.prj04.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDto {
  private int boardNo;
  private String title;
  private String contents;
}
