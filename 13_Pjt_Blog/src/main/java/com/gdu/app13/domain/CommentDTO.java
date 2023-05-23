package com.gdu.app13.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
  private int commentNo;
  private String content;
  private int state;
  private int depth;
  private int groupNo;
  private Date createdAt;
  private int blogNo;
  private MemberDTO memberDTO;
}
