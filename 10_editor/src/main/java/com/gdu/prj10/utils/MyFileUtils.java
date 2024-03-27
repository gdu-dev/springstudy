package com.gdu.prj10.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtils {

  // 현재 날짜
  public static final LocalDate TODAY = LocalDate.now();
  
  // 업로드 경로 반환
  public String getUploadPath() {
    return "/upload" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(TODAY);
  }
  
  // 저장될 파일명 반환
  public String getFilesystemName(String originalFilename) {
    String extName = null;
    if(originalFilename.endsWith(".tar.gz")) {
      extName = ".tar.gz";
    } else {
      extName = originalFilename.substring(originalFilename.lastIndexOf("."));
    }
    return UUID.randomUUID().toString().replace("-", "") + extName;
  }
  
}
