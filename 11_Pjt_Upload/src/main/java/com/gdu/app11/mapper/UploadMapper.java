package com.gdu.app11.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.domain.UploadDTO;

@Mapper
public interface UploadMapper {
	public int addUpload(UploadDTO uploadDTO);
	public int addAttach(AttachDTO attachDTO);
}
