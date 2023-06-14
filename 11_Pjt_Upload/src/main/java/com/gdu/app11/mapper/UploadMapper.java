package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.domain.UploadDTO;

@Mapper
public interface UploadMapper {

  public int getUploadCount();
  public List<UploadDTO> getUploadList(Map<String, Object> map);
  public int addUpload(UploadDTO uploadDTO);
  public int addAttach(AttachDTO attachDTO);
  public UploadDTO getUploadByNo(int uploadNo);
  public List<AttachDTO> getAttachList(int uploadNo);
  public AttachDTO getAttachByNo(int attachNo);
  public int increaseDownloadCount(int attachNo);
  public int removeUpload(int uploadNo);
  public int modifyUpload(UploadDTO uploadDTO);
  public int removeAttach(int attachNo);
  public List<AttachDTO> getAttachListInYesterday();

}
