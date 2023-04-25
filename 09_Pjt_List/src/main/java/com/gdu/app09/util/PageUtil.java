package com.gdu.app09.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PageUtil {

	private int page;              // 현재 페이지(파라미터로 받아온다)
	private int totalRecord;       // 전체 레코드 개수(DB에서 구해온다)
	private int recordPerPage;     // 한 페이지에 표시할 레코드 개수(파라미터로 받아온다)
	private int begin;             // 한 페이지에 표시할 레코드의 시작 번호(계산한다)
	private int end;               // 한 페이지에 표시할 레코드의 종료 번호(계산한다)
	
	private int pagePerBlock = 5;  // 한 블록에 표시할 페이지의 개수(임의로 정한다)
	private int totalPage;         // 전체 페이지 개수(계산한다)
	private int beginPage;         // 한 블록에 표시할 페이지의 시작 번호(계산한다)
	private int endPage;           // 한 블록에 표시할 페이지의 종료 번호(계산한다)
	
	public void setPageUtil(int page, int totalRecord, int recordPerPage) {
		
		// page, totalRecord, recordPerPage 저장
		this.page = page;
		this.totalRecord = totalRecord;
		this.recordPerPage = recordPerPage;
		
		// begin, end 계산
		/*
			totalRecord=26, recordPerPage=5인 상황
			page	begin	end
			1		1		5
			2		6		10
			3		11		15
			4		16		20
			5		21		25
			6		26		26
		*/
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		// totalPage 계산
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		
		// beginPage, endPage 계산
		/*
			totalPage=6, pagePerBlock=4인 상황
			block(page)	beginPage	endPage
			1(1~4)		1			4
			2(5~6)		5			6
		*/
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
	}
	
	
}
