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
  public UserDTO selectUserByEmail(String email);
  public SleepUserDTO selectSleepUserByEmail(String email);
  public LeaveUserDTO selectLeaveUserByEmail(String email);
  public int insertUser(UserDTO userDTO);
  public UserDTO selectUserByUserDTO(UserDTO userDTO);
  public int insertUserAccess(String id);
  public int updateUserAccess(String id);
  public int insertLeaveUser(LeaveUserDTO leaveUserDTO);
  public int deleteUser(String id);
  
  
  
  
  
  
}
