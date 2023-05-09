package com.gdu.app11.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
	public void getUploadList(Model model);
	public int addUpload(MultipartHttpServletRequest multipartRequest);
}
