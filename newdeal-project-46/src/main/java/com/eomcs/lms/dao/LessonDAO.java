package com.eomcs.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class LessonDAO {

  public List<Lesson> findAll() throws Exception {
    DriverManager.registerDriver(new Driver());
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select lno, title, cont ,sdt, edt, tot_hr, day_hr from lesson");)
    { 
      ArrayList<Lesson> list = new ArrayList<>(); 

      while(rs.next()) {
        Lesson lesson= new Lesson();
        lesson.setNo(rs.getInt("lno"));
        lesson.setTitle((rs.getString("title")));
        lesson.setContents(((rs.getString("cont"))));
        lesson.setStartDate(((rs.getDate("sdt"))));
        lesson.setEndDate((rs.getDate("edt")));
        lesson.setTotalHours(((rs.getInt("tot_hr"))));
        lesson.setDayHours((rs.getInt("day_hr")));
        
        list.add(lesson);
      }
      return list;
    }
  }

  public Lesson findByNo(int no) throws Exception{
    DriverManager.registerDriver(new Driver());
    try(
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select lno, title, cont ,sdt, edt, tot_hr, day_hr"
                + " from lesson"
                + " where lno=" + no);
        ) {
      if(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lno"));
        lesson.setTitle(rs.getString("title"));
        lesson.setContents((rs.getString("cont")));
        lesson.setStartDate((rs.getDate("sdt")));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHours((rs.getInt("tot_hr")));
        lesson.setDayHours(rs.getInt("day_hr"));
        return lesson;
      }else {
        return null;
      }
    }
  }

  public int insert(Lesson lesson) throws Exception {
    DriverManager.registerDriver(new Driver());
    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb",
            "study" ,"1111");
        Statement stmt = con.createStatement();
        ) {

      //SQL서버에 전송
      return stmt.executeUpdate("insert into lesson(title, cont, sdt, edt, tot_hr, day_hr, mno)"
          + " values('"+lesson.getTitle()+"','"
          + lesson.getContents()+"','"
          + lesson.getStartDate()+"','"
          + lesson.getEndDate()+ "','"
          + lesson.getTotalHours()+ "','"
          + lesson.getDayHours() + "'"
              + ", 1)" );
    } 
  }

  public int update(Lesson lesson) throws Exception {
    Connection con = null;;
    Statement stmt =null;;

    // 이전 문법 방식 - 현업에서 JDBC를 쓴다면 이 방식을 더 많이 쓴다
    try {
      DriverManager.registerDriver(new Driver());

      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb",
          "study" ,"1111");
      stmt = con.createStatement();

      return stmt.executeUpdate("update lesson set cont='"+
          lesson.getContents() + 
          "' where lno =" + lesson.getNo());

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
      
      return stmt.executeUpdate("delete from lesson where lno=" + no);
      
    }finally {
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }
  }
}

