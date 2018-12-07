package com.eomcs.lms;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// AppConfig를 직접 지정하면 할필요없지만 그 외에는 반드시 붙여야한다.
@Configuration


// Spring IoC Container에게 객체를 패키지 이름을 알려주면
// 그 패키지를 뒤져서 @Component가 붙은 클래스에 대해
// 인스턴스를 자동으로 생성해준다.
// => XML 설정에 추가되어 있다면 다음은 제거한다. 
//@ComponentScan("com.eomcs.lms")

// Spring IoC 컨테이너에게 프로퍼티 파일을 로딩할 것을 명령한다.
@PropertySource("classpath:/com/eomcs/lms/conf/jdbc.properties")

// MyBatis의 DAO인터페이스 구현체를 자동 생성하는 도우미를 추가한다.
// => DAO 인터페이스가 들어있는 패키지를 지정한다.
// => 자동 생성된 DAO구현체가 SQL을 찾을 때 인터페이스의 전체 이름으로 찾는다.
// => 따라서 SQL Mapper파일의 namespace 이름은 인터페이스의 전체 이름과 같아야 한다.
// => 인터페이스의 메서드 이름, 파라미터, 리턴 타입은 SQL Mapper의 아이디(id), 파라미터 타입, 리턴 타입이 같아야 한다.
@MapperScan("com.eomcs.lms.dao")
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
  
  // Spring WebMVC에 기본으로 설정되어 있는 ViewResolver를 InternalResourceViewResolver로 교체한다.
  // 페이지 컨트롤러가 리턴하는 URL 앞/뒤에 설정된 경로를 붙인다.
  // => XML에서 ViewResolver 객체를 준비했다면 다음 메서드는 제거한다.
/*  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vs =
        new InternalResourceViewResolver();
    vs.setPrefix("/WEB-INF/jsp/");
    vs.setSuffix(".jsp");
    vs.setViewClass(JstlView.class);
    
    return vs;
  }*/
  
}
