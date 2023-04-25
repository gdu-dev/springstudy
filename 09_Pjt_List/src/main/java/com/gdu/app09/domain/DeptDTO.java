package com.gdu.app09.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptDTO {
	private int departmentId;
	private String departmentName;
	private int managerId;
	private int locationId;
}
