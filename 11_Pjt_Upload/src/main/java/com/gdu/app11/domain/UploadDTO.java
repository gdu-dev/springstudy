package com.gdu.app11.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadDTO {
	private int uploadNo;
	private String uploadTitle;
	private String uploadContent;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
}
