package com.gdu.prj10.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {
  ResponseEntity<Map<String, Object>> summernoteImageUpload(MultipartHttpServletRequest multipartRequest);
}
