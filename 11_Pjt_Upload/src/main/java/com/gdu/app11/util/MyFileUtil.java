package com.gdu.app11.util;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtil {

	// String filesystemName 만들기
	public String getFilesystemName(String originName) {
		
		// 원래 첨부 파일의 확장자 꺼내기
		String extName = null;
		
		// 확장자에 마침표(.)가 포함된 예외 상황 처리
		if(originName.endsWith("tar.gz")) {
			extName = "tar.gz";
		} else {
			// String.split(정규식)
			// 정규식에서 마침표(.)는 모든 문자를 의미하므로 이스케이프 처리하거나 문자클래스로 처리한다.
			// 이스케이프 처리 : \.
			// 문자클래스 처리 : [.]
			String[] array = originName.split("\\.");
			extName = array[array.length - 1];
		}
		
		// 결과 반환
		// UUID.extName
		
		
	}
	
}







