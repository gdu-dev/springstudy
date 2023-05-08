package com.gdu.app11.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
	public int addUpload(MultipartHttpServletRequest multipartRequest);
}
