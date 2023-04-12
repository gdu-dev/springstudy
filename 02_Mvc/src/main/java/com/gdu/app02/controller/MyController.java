package com.gdu.app02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
	@Controller
	안녕. 난 컨트롤러야.
	@Component를 포함하고 있어서 자동으로 Spring Container에 Bean으로 등록되지.
	나는 스프링에 의해서 사용되고 있어.
*/

@Controller
public class MyController {

	// 메소드 : 요청과 응답을 처리하는 단위
	
	/*
		메소드 작성 방법
		1. 반환타입 : String (응답할 Jsp의 이름을 작성한다.)
		2. 메소드명 : 아무 일도 안 한다. 아무 의미 없다.
		3. 매개변수 : 요청에 응답에 따라 다르다. (요청이 필요한 경우 HttpServletRequest, 응답이 필요하면 HttpServletResponse 등)
	*/
	
	/*
		@RequestMapping
		1. value  : URL Mapping을 작성한다.    (동작할 주소를 작성한다.)
		2. method : Request Method를 작성한다. (GET, POST, PUT, DELETE 등)
	*/
	/*
		@RequestMapping(value="/", method=RequestMethod.GET)
		URL Mapping이 "/"이면 context path 경로를 의미한다. (http://localhost:9090/app02)
	*/
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index";  // ViewResoler에 의해서 해석된다. (servlet-context.xml을 참고하자.)
		                 // /WEB-INF/views/index.jsp
	}
	
	/*
		@RequestMapping 작성 예시
		@RequestMapping(value="/list.do", method=RequestMethod.GET)    정식 버전
		@RequestMapping(value="list.do", method=RequestMethod.GET)     value는 슬래시(/)로 시작하지 않아도 된다.
		@RequestMapping(value="/list.do")                              GET 방식의 method는 생략할 수 있다.
		@RequestMapping("/list.do")                                    value 속성만 작성하는 경우네는 값만 작성할 수 있다.
	*/
	@RequestMapping("/list.do")
	public String list() {
		return "board/list";  // 실제 처리되는 경로 : /WEB-INF/views/board/list.jsp
		/*
			return "/board/list";  슬래시(/)로 시작한 Jsp 경로
			실제 처리되는 경로 : /WEB-INF/views//board/list.jsp  (중간에 슬래시가 2개 포함된다.)
			하지만, 실제로는 /WEB-INF/views/board/list.jsp 경로로 처리된다.
		*/
	}
	
}
