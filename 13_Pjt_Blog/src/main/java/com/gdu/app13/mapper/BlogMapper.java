package com.gdu.app13.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app13.domain.BlogDTO;

@Mapper
public interface BlogMapper {
  public int getBlogCount();
  public List<BlogDTO> getBlogList(Map<String, Object> map);
  public int addBlog(BlogDTO blogDTO);
}
