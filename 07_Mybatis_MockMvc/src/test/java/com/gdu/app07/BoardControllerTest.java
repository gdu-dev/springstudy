package com.gdu.app07;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// JUnit4
@RunWith(SpringJUnit4ClassRunner.class)

// ContextConfiguration
// 테스트에서 사용할 Bean이 @Component로 생성되었기 때문에 component-scan이 작성된 servlet-context.xml의 경로를 작성한다.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

// 테스트 순서를 메소드명의 알파벳순으로 수행
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

// WebApplicationContext를 사용하기 위해서 필요한 애너테이션
@WebAppConfiguration

public class BoardControllerTest {

	/*
		Mock 테스트
		
		1. 가상 MVC 테스트이다.
		2. Controller를 테스트할 수 있는 통합 테스트이다.
		3. method + mapping을 이용해서 테스트를 진행한다.
	*/
	
	// Mock 테스트를 수행하는 객체
	// WebApplicationContext에 의해서 생성된다.
	private MockMvc mockMvc;
	
	// @WebApplication이 있어야 자동 주입(@Autowired)이 가능하다.
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardControllerTest.class);
	
	// @Before
	// 1. 모든 @Test 수행 이전에 실행된다.
	// 2. MockMvc mockMvc 객체를 @Before에서 build한다.
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@Test
	public void a1삽입테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/add.do")              // @PostMapping("/board/add.do")
					.param("title", "테스트제목")     // 파라미터
					.param("content", "테스트내용")   // 파라미터
					.param("writer", "테스트작성자")) // 파라미터
						.andReturn()                    // 삽입결과
						.getFlashMap()                  // FlashAttrubute에 저장된 결과 확인 
							.toString());
	}
	
	@Test
	public void a2수정테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/modify.do")           // @PostMapping("/board/modify.do")
					.param("title", "테스트제목2")    // 파라미터
					.param("content", "테스트내용2")  // 파라미터
					.param("boardNo", "1"))           // 파라미터
						.andReturn()                    // 수정결과
						.getFlashMap()                  // FlashAttrubute에 저장된 결과 확인 
							.toString());
	}
	
	@Test
	public void a3상세조회테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/detail.do")  // @GetMapping("/board/detail.do")
					.param("boardNo", "1")) // 파라미터
						.andReturn()          // 상세조회결과
						.getModelAndView()    // Model에 저장된 조회 결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
						.getModelMap()        // ModelAndView에서 Model을 가져옴
							.toString());
	}
	
	@Test
	public void a4목록테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/list.do")) // @GetMapping("/board/list.do")
					.andReturn()          // 목록조회결과
					.getModelAndView()    // Model에 저장된 조회 결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
					.getModelMap()        // ModelAndView에서 Model을 가져옴
						.toString());
	}
	
	@Test
	public void a5삭제테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/remove.do")   // @PostMapping("/board/remove.do")
					.param("boardNo", "1"))   // 파라미터
						.andReturn()            // 수정결과
						.getFlashMap()          // FlashAttrubute에 저장된 결과 확인 
							.toString());
	}
	
}
