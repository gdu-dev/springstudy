package com.gdu.app13.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
  private int memberNo;
  private String id;
  private String pw;
  private String name;
}
