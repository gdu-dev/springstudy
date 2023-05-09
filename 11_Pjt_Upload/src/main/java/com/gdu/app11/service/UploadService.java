package com.gdu.app11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
	public void getUploadList(HttpServletRequest request, Model model);
	public int addUpload(MultipartHttpServletRequest multipartRequest);
	public void getUploadByNo(int uploadNo, Model model);
	public ResponseEntity<byte[]> display(int attachNo);
}
