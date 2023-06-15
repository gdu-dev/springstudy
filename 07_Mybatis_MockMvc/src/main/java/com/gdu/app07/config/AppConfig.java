package com.gdu.app07.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*

  참고.
  
  @Autowired
  private BoardService boardService;
  
  위 코드처럼 인터페이스를 찾아서 Bean을 바인딩하는 것이 스프링의 기본 동작이다. 이것을 AutoProxy라고 한다.
  트랜잭션을 처리할 때도 AutoProxy로 동작한다.
  
  만약,
  @Autowired
  private BoardServiceImpl boardService;
    
  트랜잭션을 처리할 때
  위 코드처럼 인터페이스가 아닌 클래스로 바인딩하는 상황이라면,
  @EnableTransactionManagement(proxyTargetClass=true) 옵션을 사용해서 ProxyTarget(프록시대상)을 Class(클래스)로 지정해야 한다.

*/

@PropertySource(value={"classpath:application.properties"})  // application.properties 파일 읽기
@EnableTransactionManagement                                 // 트랜잭션 허용
@Configuration
public class AppConfig {

  @Autowired
  private Environment env;
  
  // HikaryConfig Bean
  @Bean
  public HikariConfig hikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name"));
    hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));
    hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
    hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
    return hikariConfig;
  }
  
  // HikariDataSource Bean
  @Bean(destroyMethod="close")
  public HikariDataSource hikariDataSource() {
    return new HikariDataSource(hikariConfig());
  }
  
  // SqlSessionFactory Bean
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(hikariDataSource());
    bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis.config-location")));
    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
    return bean.getObject();
  }
  
  // SqlSessionTemplate Bean (기존의 SqlSession)
  @Bean
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory());
  }
  
  // TransactionManager Bean
  @Bean
  public TransactionManager transactionManager() {
    return new DataSourceTransactionManager(hikariDataSource());
  }
  
}