package com.gdu.app13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO {
  private int blogNo;
  private String title;
  private String content;
  private int hit;
  private Date createdAt;
  private Date modifiedAt;
  private MemberDTO memberDTO;  // private int memberNo;
}
