package com.gdu.app12.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app12.domain.LeaveUserDTO;
import com.gdu.app12.domain.SleepUserDTO;
import com.gdu.app12.domain.UserDTO;

@Mapper
public interface UserMapper {
  public UserDTO selectUserById(String id);
  public SleepUserDTO selectSleepUserById(String id);
  public LeaveUserDTO selectLeaveUserById(String id);
}
