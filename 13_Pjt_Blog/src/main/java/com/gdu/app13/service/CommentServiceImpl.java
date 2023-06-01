package com.gdu.app13.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.app13.domain.CommentDTO;
import com.gdu.app13.domain.MemberDTO;
import com.gdu.app13.mapper.CommentMapper;
import com.gdu.app13.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

  private CommentMapper commentMapper;
  private PageUtil pageUtil;
  
  @Override
  public Map<String, Object> addComment(HttpServletRequest request) {
    
    String content = request.getParameter("content");
    int blogNo = Integer.parseInt(request.getParameter("blogNo"));
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberNo(memberNo);
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setContent(content);
    commentDTO.setBlogNo(blogNo);
    commentDTO.setMemberDTO(memberDTO);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isAdd", commentMapper.addComment(commentDTO) == 1);
    
    return map;
    
  }

  @Override
  public Map<String, Object> getCommentCount(int blogNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, Object> getCommentList(HttpServletRequest request) {
    
    int blogNo = Integer.parseInt(request.getParameter("blogNo"));
    int page = Integer.parseInt(request.getParameter("page"));
    int commentCount = commentMapper.getCommentCount(blogNo);
    int recordPerPage = 3;
    
    pageUtil.setPageUtil(page, commentCount, recordPerPage);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("blogNo", blogNo);
    map.put("begin", pageUtil.getBegin());
    map.put("end", pageUtil.getEnd());
    
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("commentList", commentMapper.getCommentList(map));
    result.put("pageUtil", pageUtil);
    
    return result;
    
  }
  
  @Override
  public Map<String, Object> addReply(HttpServletRequest request) {
    
    String content = request.getParameter("content");
    int blogNo = Integer.parseInt(request.getParameter("blogNo"));
    int groupNo = Integer.parseInt(request.getParameter("groupNo"));
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberNo(memberNo);
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setContent(content);
    commentDTO.setBlogNo(blogNo);
    commentDTO.setGroupNo(groupNo);
    commentDTO.setMemberDTO(memberDTO);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isAdd", commentMapper.addReply(commentDTO) == 1);
    
    return map;
    
  }

}










