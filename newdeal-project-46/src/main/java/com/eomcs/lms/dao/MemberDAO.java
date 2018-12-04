package com.eomcs.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

public class MemberDAO {

  public List<Member> findAll() throws Exception {
    DriverManager.registerDriver(new Driver());
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select mno, name, email ,pwd, photo, tel, cdt from member");)
    { 
      ArrayList<Member> list = new ArrayList<>(); 

      while(rs.next()) {
        Member member= new Member();
        member.setNo(rs.getInt("mno"));
        member.setName(rs.getString("name"));
        member.setEmail((rs.getString("email")));
        member.setPassword((rs.getString("pwd")));
        member.setPhoto(rs.getString("photo"));
        member.setTel((rs.getString("tel")));
        member.setRegisteredDate(rs.getDate("cdt"));
        
        list.add(member);
      }
      return list;
    }
  }

  public Member findByNo(int no) throws Exception{
    DriverManager.registerDriver(new Driver());
    try(
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select mno, name, email ,pwd, photo, tel, cdt"
                + " from member"
                + " where mno=" + no);
        ) {
      if(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("mno"));
        member.setName(rs.getString("name"));
        member.setEmail((rs.getString("email")));
        member.setPassword((rs.getString("pwd")));
        member.setPhoto(rs.getString("photo"));
        member.setTel((rs.getString("tel")));
        member.setRegisteredDate(rs.getDate("cdt"));
        return member;
      }else {
        return null;
      }
    }
  }

  public int insert(Member member) throws Exception {
    DriverManager.registerDriver(new Driver());
    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb",
            "study" ,"1111");
        Statement stmt = con.createStatement();
        ) {

      //SQL서버에 전송
      return stmt.executeUpdate("insert into member(name,email,pwd,photo,tel)"
          + " values('"+member.getName()+"','"
          + member.getEmail()+"','"
          + member.getPassword()+"','"
          + member.getPhoto() + "','"
          + member.getTel() + "')" );
    } 
  }

  public int update(Member member) throws Exception {
    Connection con = null;;
    Statement stmt =null;;

    // 이전 문법 방식 - 현업에서 JDBC를 쓴다면 이 방식을 더 많이 쓴다
    try {
      DriverManager.registerDriver(new Driver());

      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb",
          "study" ,"1111");
      stmt = con.createStatement();

      return stmt.executeUpdate("update member set name='"+
          member.getName() + 
          "' where mno =" + member.getNo());

    }finally { // catch는 없어도 예외가 발생하면 알아서 에러를 띄워준다
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }
  }

  public int delete(int no) throws Exception{
    Connection con = null;;
    Statement stmt = null;;

    try {
      DriverManager.registerDriver(new Driver());
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb",
          "study" ,"1111");
      stmt = con.createStatement();
      
      return stmt.executeUpdate("delete from member where mno=" + no);
      
    }finally {
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }
  }
}

