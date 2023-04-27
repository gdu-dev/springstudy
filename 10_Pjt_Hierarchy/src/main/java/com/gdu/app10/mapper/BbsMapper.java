package com.gdu.app10.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app10.domain.BbsDTO;

@Mapper
public interface BbsMapper {
	public int getBbsCount();
	public List<BbsDTO> getBbsList(Map<String, Object> map);
	public int addBbs(BbsDTO bbsDTO);
	public int removeBbs(int bbsNo);
}
