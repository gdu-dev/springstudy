package com.gdu.app12.aspect;

import com.gdu.app12.util.SecurityUtil;

public class MainClass {

  public static void main(String[] args) {
    
    SecurityUtil securityUtil = new SecurityUtil();
    
    System.out.println(securityUtil.getSha256("1111"));
    System.out.println(securityUtil.getSha256("2222"));
    System.out.println(securityUtil.getSha256("9999"));

  }

}
