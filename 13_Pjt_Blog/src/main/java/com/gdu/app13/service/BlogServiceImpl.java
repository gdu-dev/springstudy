package com.gdu.app13.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app13.domain.BlogDTO;
import com.gdu.app13.domain.MemberDTO;
import com.gdu.app13.domain.SummernoteImageDTO;
import com.gdu.app13.mapper.BlogMapper;
import com.gdu.app13.util.MyFileUtil;
import com.gdu.app13.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

  private BlogMapper blogMapper;
  private MyFileUtil myFileUtil;
  private PageUtil pageUtil;
  
  @Override
  public void loadBlogList(HttpServletRequest request, Model model) {
    
    Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt1.orElse("1"));
    int blogCount = blogMapper.getBlogCount();
    int recordPerPage = 10;
    
    pageUtil.setPageUtil(page, blogCount, recordPerPage);
    
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("begin", pageUtil.getBegin());
    map.put("end", pageUtil.getEnd());
    
    List<BlogDTO> blogList = blogMapper.getBlogList(map);
    
    model.addAttribute("blogList", blogList);
    model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/blog/list.do"));
    model.addAttribute("beginNo", blogCount - (page - 1) * recordPerPage);
    
  }

  @Transactional(readOnly=true)
  @Override
  public void addBlog(HttpServletRequest request, HttpServletResponse response) {
    
    // 요청 파라미터
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
    
    /*** BLOG_T ***/
    
    // DB로 보낼 BlogDTO 만들기
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberNo(memberNo);
    BlogDTO blogDTO = new BlogDTO();
    blogDTO.setTitle(title);
    blogDTO.setContent(content);
    blogDTO.setMemberDTO(memberDTO);
    
    // DB로 BlogDTO 보내기 (삽입)
    int addResult = blogMapper.addBlog(blogDTO);

    /*** SUMMERNOTE_IMAGE_T ***/
    
    Document document = Jsoup.parse(content);
    Elements elements = document.getElementsByTag("img");
    
    if(elements != null) {
      for(Element element : elements) {
        String src = element.attr("src");
        String filesystemName = src.substring(src.lastIndexOf("/") + 1);
        SummernoteImageDTO summernoteImageDTO = new SummernoteImageDTO();
        summernoteImageDTO.setFilesystemName(filesystemName);
        summernoteImageDTO.setBlogNo(blogDTO.getBlogNo());
        blogMapper.addSummernoteImage(summernoteImageDTO);
      }
    }
    
    // 응답
    try {
      
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if(addResult == 1) {
        out.println("alert('블로그가 작성되었습니다.');");
        out.println("location.href='" + request.getContextPath() + "/blog/list.do';");
      } else {
        out.println("alert('블로그 작성이 실패했습니다.');");
        out.println("history.back();");
      }
      out.println("</script>");
      out.flush();
      out.close();
      
    } catch(Exception e) {
      e.printStackTrace();
    }

  }
  
  @Override
  public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest) {
    
    /*

      var formData = new FormData();
      formData.append('file', 첨부된파일);
      
      $.ajax({
        data: formData
      }
      
    */
    
    // formData에 저장된 file 꺼내기
    MultipartFile multipartFile = multipartRequest.getFile("file");
    
    // HDD 저장할 경로
    String summernoteImagePath = myFileUtil.getSummernoteImagePath();
    
    // HDD 저장할 경로 없으면 만들기
    File dir = new File(summernoteImagePath);
    if(dir.exists() == false) {
      dir.mkdirs();
    }
    
    // HDD 저장할 파일의 이름(UUID.확장자)
    String filesystemName = myFileUtil.getFilesystemName(multipartFile.getOriginalFilename());
    
    // HDD 저장
    try {
      File file = new File(dir, filesystemName);
      multipartFile.transferTo(file);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    // HDD에 저장된 파일의 확인을 위한 mapping 값을 반환(servlet-context.xml 참고할 것)
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("src", multipartRequest.getContextPath() + "/imageLoad/" + filesystemName);
    
    return map;
    
  }
  
  @Override
  public int increaseHit(int blogNo) {
    return blogMapper.increseHit(blogNo);
  }
  
  @Override
  public void loadBlog(int blogNo, Model model) {
    model.addAttribute("blog", blogMapper.getBlogByNo(blogNo));
  }
  
}
