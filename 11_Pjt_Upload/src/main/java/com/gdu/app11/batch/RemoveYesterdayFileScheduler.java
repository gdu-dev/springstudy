package com.gdu.app11.batch;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableScheduling
@Component
public class RemoveYesterdayFileScheduler {

  private final MyFileUtil myFileUtil;
  private final UploadMapper uploadMapper;
  
  // @Scheduled(cron="0 0/1 * 1/1 * ?") // 테스트를 위해서 1분마다 스케쥴러를 동작시키기
  @Scheduled(cron="0 0 2 1/1 * ?")      // www.cronmaker.com에서 생성한 매일 새벽 2시 정보에서 마지막 필드 *를 지워줌
  public void execute() {               // 메소드명은 아무 의미 없다.
    
    
    // 1. 어제 업로드 된 첨부 파일들의 정보를 DB에서 가져온다.
    List<AttachDTO> yesterdayList = uploadMapper.getAttachListInYesterday();
    
    
    // 2. DB에서 가져온 List<AttachDTO>를 List<Path>로 변환한다. (Path는 경로+파일명로 구성된다.)
    List<Path> yesterdayPathList = new ArrayList<Path>();
    if(yesterdayList != null && yesterdayList.isEmpty() == false) {
      for(AttachDTO attachDTO : yesterdayList) {
        // 2-1) 어제 첨부된 파일들
        yesterdayPathList.add(new File(attachDTO.getPath(), attachDTO.getFilesystemName()).toPath());  // Path 만들기 : File객체.toPath()
        // 2-2) 어제 첨부된 썸네일들
        if(attachDTO.getHasThumbnail() == 1) {
          yesterdayPathList.add(new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName()).toPath());
        }
      }
    }
    
    
    // 3. 어제 업로드 된 첨부 파일들의 저장 경로를 가져온다.
    String yesterdayPath = myFileUtil.getYesterdayPath();
    
    
    // 4. 어제 업로드 된 첨부 파일 목록(HDD에서 확인) 중에서 yesterdayPathList에는 정보가 없는 파일 목록을 File[] removeFiles에 추가한다.
    File dir = new File(yesterdayPath);
    File[] removeFiles = dir.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {  // true를 반환하면 File[] removeFiles에 포함된다. 매개변수 File dir, String name은 HDD에 저장된 파일을 의미한다.
        // DB에 있는 목록  : pathList               - 이미 Path
        // HDD에 있는 파일 : File dir, String name  - File.toPath() 처리해서 Path로 변경
        return yesterdayPathList.contains(new File(dir, name).toPath()) == false;
      }
    });
    
    
    // 5. File[] removeFiles의 파일을 모두 삭제한다.
    if(removeFiles != null && removeFiles.length != 0) {
      for(File removeFile : removeFiles) {
        removeFile.delete();
      }
    }
    
    
  }
  
}
