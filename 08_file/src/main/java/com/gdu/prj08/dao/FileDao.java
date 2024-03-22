package com.gdu.prj08.dao;

import com.gdu.prj08.dto.FileDto;
import com.gdu.prj08.dto.HistoryDto;

public interface FileDao {
  int insertHistory(HistoryDto history);
  int insertFile(FileDto file);
}
