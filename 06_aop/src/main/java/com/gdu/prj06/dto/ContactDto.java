package com.gdu.prj06.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContactDto {
  private int contactNo;
  private String name;
  private String mobile;
  private String email;
  private String address;
  private String createdAt;
}
