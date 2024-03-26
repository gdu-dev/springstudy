package com.gdu.prj09.utils;

import lombok.Data;

@Data
public class MyPageUtils {

  private int total;
  private int display;
  private int page;
  private int begin;
  private int end;
  
  private int pagePerBlock = 10;
  private int totalPage;
  private int beginPage;
  private int endPage;
  
  public void setPaging(int total, int display, int page) {
    
    this.total = total;
    this.display = display;
    this.page = page;
    
    begin = (page - 1) * display + 1;
    end = begin + display - 1;
    
    totalPage = (int) Math.ceil((double)total / display);
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1);
    
  }
  
  public String getAsyncPaging() {
   
    StringBuilder builder = new StringBuilder();
    
    // <
    
    // 1 2 3 4 5 6 7 8 9 10
    
    // >
    
    return builder.toString();
    
  }
  
}








