package com.gdu.app13.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app13.domain.CommentDTO;

@Mapper
public interface CommentMapper {
  public int addComment(CommentDTO commentDTO);
}
