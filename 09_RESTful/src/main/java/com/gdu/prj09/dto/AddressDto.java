package com.gdu.prj09.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDto {
  private String zonecode;
  private String roadAddress;
  private String jibunAddress;
  private String detailAddress;
  private int memberNo;
}
