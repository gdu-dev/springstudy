package com.gdu.prj07;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gdu.prj07.controller.ContactController;
import com.gdu.prj07.service.ContactService;

import lombok.extern.slf4j.Slf4j;

// JUnit4
@RunWith(SpringJUnit4ClassRunner.class)

// ContactService 타입의 ContactServiceImpl bean 이 등록된 파일
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

// WebApplicationContext 타입의 bean 을 Spring Container 에 만든다.
// @WebAppConfiguration

// 테스트 수행 순서 (메소드 이름 순)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

// 로그
@Slf4j

// @SpringJUnitWebConfig(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ContactMockTest {
  @Autowired
  private ContactService contactService;
//  @Autowired
//  private WebApplicationContext webApplicationContext;
  
  // 테스트 수행을 위한 MockMvc 객체 선언
  private MockMvc mockMvc;
  
  // MockMvc 객체 생성 (테스트 수행 이전에 생성한다.)
  /*@Before
  public void setUp() {
    mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
              .build();
  }*/
  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new ContactController(contactService)).build();
  }

  
  @Test
  public void 테스트01_MockMvc생성확인() {
    assertNotNull(mockMvc);
  }
  
  @Test
  public void 테스트02_삽입() throws Exception {
    
    MvcResult mvcResult = mockMvc
                            .perform(MockMvcRequestBuilders
                                       .post("/contact/register.do")
                                       .param("name", "테스트이름")
                                       .param("mobile", "테스트모바일")
                                       .param("email", "테스트이메일")
                                       .param("address", "테스트주소"))
                            .andReturn();
    
    log.info(mvcResult.getFlashMap().toString());
    
  }

  
}