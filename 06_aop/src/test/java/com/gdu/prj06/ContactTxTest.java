package com.gdu.prj06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.prj06.service.ContactService;

// JUnit4
@RunWith(SpringJUnit4ClassRunner.class)

// ContactService 타입의 ContactServiceImpl bean 이 등록된 파일
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class ContactTxTest {
  
  @Autowired
  private ContactService contactService;
  
  @Test
  public void 트랜잭션_테스트() {
    contactService.txTest();
  }
  
}
