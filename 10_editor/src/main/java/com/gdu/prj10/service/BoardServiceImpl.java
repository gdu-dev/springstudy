package com.gdu.prj10.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.prj10.utils.MyFileUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

  private final MyFileUtils myFileUtils;
  
  @Override
  public ResponseEntity<Map<String, Object>> summernoteImageUpload(MultipartHttpServletRequest multipartRequest) {
    // TODO Auto-generated method stub
    return null;
  }

}
