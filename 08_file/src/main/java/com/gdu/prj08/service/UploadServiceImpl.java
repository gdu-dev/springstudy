package com.gdu.prj08.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.prj08.utils.MyFileUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UploadServiceImpl implements UploadService {

  private final MyFileUtils myFileUtils;
  
  @Override
  public int upload1(MultipartHttpServletRequest multipartRequest) {
    
    // 첨부 파일 목록
    List<MultipartFile> files = multipartRequest.getFiles("files");
    
    // 첨부 파일 목록 순회
    for(MultipartFile multipartFile : files) {
      
      // 첨부 파일 존재 여부 확인
      if(multipartFile != null && !multipartFile.isEmpty()) {
        
        // 첨부 파일 경로
        String uploadPath = myFileUtils.getUploadPath();
        
        // 첨부 파일 경로 디렉터리 만들기
        File dir = new File(uploadPath);
        if(!dir.exists()) {
          dir.mkdirs();
        }
        
        // 첨부 파일 원래 이름
        String originalFilename = multipartFile.getOriginalFilename();
        
        // 첨부 파일 저장 이름
        String filesystemName = myFileUtils.getFilesystemName(originalFilename);
        
        // 첨부 파일 File 객체
        File file = new File(dir, filesystemName);
        
        // 저장
        try {          
          multipartFile.transferTo(file);
        } catch (Exception e) {
          e.printStackTrace();
        }
        
      }
      
    }
    
    return 1;
    
  }

}
