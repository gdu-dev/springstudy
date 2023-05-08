package com.gdu.app11.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.AttachDTO;

@Mapper
public interface UploadMapper {
	public int addAttach(AttachDTO attachDTO);
}
