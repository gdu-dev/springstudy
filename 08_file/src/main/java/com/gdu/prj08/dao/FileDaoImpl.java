package com.gdu.prj08.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.gdu.prj08.dto.FileDto;
import com.gdu.prj08.dto.HistoryDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileDaoImpl implements FileDao {

  private final SqlSessionTemplate sqlSessionTemplate;
  
  @Override
  public int insertHistory(HistoryDto history) {
    return sqlSessionTemplate.insert("com.gdu.prj08.mybatis.mapper.file_t.insertHistory", history);
  }

  @Override
  public int insertFile(FileDto file) {
    return sqlSessionTemplate.insert("com.gdu.prj08.mybatis.mapper.file_t.insertFile", file);
  }

}
