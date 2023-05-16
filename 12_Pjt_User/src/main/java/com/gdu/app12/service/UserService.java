package com.gdu.app12.service;

import java.util.Map;

public interface UserService {

  public Map<String, Object> verifyId(String id);
  public Map<String, Object> verifyEmail(String email);
  public Map<String, Object> sendAuthCode(String email);
  
}
