package com.gdu.app02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bbs {
	private int bbsNo;
	private String title;
	private String createdAt;
}
