package com.gdu.prj08.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
  int upload1(MultipartHttpServletRequest multipartRequest);
  Map<String, Object> upload2(MultipartHttpServletRequest multipartRequest);
}
