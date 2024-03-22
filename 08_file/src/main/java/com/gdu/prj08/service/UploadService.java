package com.gdu.prj08.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
  int upload1(MultipartHttpServletRequest multipartRequest);
}
