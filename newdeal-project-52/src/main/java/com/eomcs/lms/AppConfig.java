package com.eomcs.lms;

import java.util.Scanner;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.dao.impl.MariaDBBoardDAO;

// Spring IoC Container에게 객체를 패키지 이름을 알려주면
// 그 패키지를 뒤져서 @Component가 붙은 클래스에 대해
// 인스턴스를 자동으로 생성해준다.
@ComponentScan("com.eomcs.lms")

// Spring IoC 컨테이너에게 프로퍼티 파일을 로딩할 것을 명령한다.
@PropertySource("classpath:/com/eomcs/lms/conf/jdbc.properties")
public class AppConfig {
  
  // Spring IoC Container가 로딩한 프로퍼티 정보를 가져오기
  // => Spring IoC Container에게 "로딩한 프로퍼티 값 중에서 jdbc.driver라는 이름을 가진 값을 꺼내 jdbcDriver 변수에 넣어줘"
  @Value("${jdbc.driver}")
  String jdbcDriver;
  
  @Value("${jdbc.url}")
  String jdbcUrl;
  
  @Value("${jdbc.username}")
  String jdbcUsername;
  
  @Value("${jdbc.password}")
  String jdbcPassword;
  
  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(this.jdbcDriver); //this를 쓰는 이유는 인스턴스 변수라는것을 확실하게 명시하기 위해
    dataSource.setUrl(this.jdbcUrl);
    dataSource.setUsername(this.jdbcUsername);
    dataSource.setPassword(this.jdbcPassword);
    return dataSource;
  }
  
  // 트랜잭션 객체를 생성할 때
  // 기본 이름으로 transactionManager라고 설정하라.
  // 다른 이름으로 설정하면
  // 트랜잭션 관련하여 다른 객체를 생성할 때
  // 그 객체가 트랜잭션 관리자를 자동으로 찾지 못한다.
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource, ApplicationContext iocContainer) throws Exception{
    // MyBatis SqlSessionFactory 준비      
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    
    // DataSource(DB 커넥션풀) 객체 주입
    factoryBean.setDataSource(dataSource);
    
    // Domain 클래스(VO;Value Object)의 별명 자동 생성하기
    factoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    
    //SQL Mapper 파일  로딩
    // => SQL Mapper 파일의 위치 정보를 Resouce 객체에 담아서 넘겨야 한다.
    // => Resource 객체는 Spring IoC Container를 통해 만들 수 있다.
    // => Spring IoC Container 객체를 얻는 방법 : 이 메서드의 파라미터에 달라고 요청해야 한다.
    factoryBean.setMapperLocations(iocContainer.getResources(
        "classpath:/com/eomcs/lms/mapper/*.xml"));
    
    return factoryBean.getObject();
  }
  
  
  
  @Bean
  public Scanner keyboard() {
    return new Scanner(System.in);
  }
  
  public BoardDAO boardDAO(SqlSessionFactory sqlSessionFactory) {
    
    return new MariaDBBoardDAO(sqlSessionFactory);
  }
}
