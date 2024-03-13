package com.gdu.prj01.anno01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Computer {
  private String model;
  private int price;
  private Calculator calculator;
}
