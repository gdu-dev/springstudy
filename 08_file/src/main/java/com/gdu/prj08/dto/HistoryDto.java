package com.gdu.prj08.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HistoryDto {
  private int historyNo;
  private String writer;
  private String ip;
}
