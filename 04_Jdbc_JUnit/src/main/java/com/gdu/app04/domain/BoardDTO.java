package com.gdu.app04.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private int board_no;
	private String title;
	private String content;
	private String writer;
	private String created_at;
	private String modified_at;
}
