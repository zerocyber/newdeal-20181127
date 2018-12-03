package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;

public class BoardUpdateCommand implements Command{
  Scanner keyboard;

  public BoardUpdateCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  public void excute() {

    Connection con = null;;
    Statement stmt =null;;

    try {
      //MariaDB JDBC Driver(java.sql.Driver) 구현체를 로딩
      DriverManager.registerDriver(new Driver());

      //DBMS에 연결하기
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb",
          "study" ,"1111");

      //SQL 전송을 담당할 객체를 준비
      stmt = con.createStatement();

      System.out.print("번호? ");
      String no = keyboard.nextLine();

      ResultSet rs = stmt.executeQuery("select cont from board where bno=" + no);

      rs.next();
      String oldContent = rs.getString("cont");
      rs.close();

      System.out.printf("내용?( 이전내용 : %s) " , oldContent);
      String content = keyboard.nextLine();

      //SQL을 서버에 전송 => 서버에서 결과를 가져올 역할을 하는 객체를 리턴
      stmt.executeUpdate("update board set cont='"+
          content + "' where bno =" + no);

      System.out.println("변경했습니다!");

    } catch (Exception e) {
      e.printStackTrace();

    } finally {
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }

  }


}
