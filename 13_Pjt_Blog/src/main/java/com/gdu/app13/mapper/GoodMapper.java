package com.gdu.app13.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodMapper {
	public int getUserGoodCount(Map<String, Object> map);
	public int getBlogGoodCount(int blogNo);
	public int addGood(Map<String, Object> map);
	public int deleteGood(Map<String, Object> map);
}
