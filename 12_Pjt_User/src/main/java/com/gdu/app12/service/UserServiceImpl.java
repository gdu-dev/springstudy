package com.gdu.app12.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gdu.app12.mapper.UserMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor  // field에 @Autowired 처리를 위해서
@Service
public class UserServiceImpl implements UserService {

  // field
  private UserMapper userMapper;
  
  @Override
  public Map<String, Object> verifyId(String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("enableId", userMapper.selectUserById(id) == null && userMapper.selectSleepUserById(id) == null && userMapper.selectLeaveUserById(id) == null);
    return map;
  }

}





