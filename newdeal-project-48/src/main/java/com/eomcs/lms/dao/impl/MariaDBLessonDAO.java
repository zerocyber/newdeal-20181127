package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;

public class MariaDBLessonDAO implements LessonDAO{

  @Override
  public Lesson findByNo(int no) throws Exception {

    DriverManager.registerDriver(new Driver());

    try(Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
        PreparedStatement stmt = 
            con.prepareStatement("select lno, title, cont ,sdt, edt, tot_hr, day_hr from lesson where lno=?");)
    {
      stmt.setInt(1, no);

      try(ResultSet rs = stmt.executeQuery()){
        if(rs.next()) {
          Lesson lesson = new Lesson();
          lesson.setNo(rs.getInt("lno"));
          lesson.setTitle(rs.getString("title"));
          lesson.setContents(rs.getString("cont"));
          lesson.setStartDate(rs.getDate("sdt"));
          lesson.setEndDate(rs.getDate("edt"));
          lesson.setTotalHours(rs.getInt("tot_hr"));
          lesson.setDayHours(rs.getInt("day_hr"));
          return lesson;
        }else {
          return null;
        }
      }
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    
    DriverManager.registerDriver(new Driver());
    
    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
        PreparedStatement stmt = con.prepareStatement("select lno, title, cont ,sdt, edt, tot_hr, day_hr from lesson");
        ResultSet rs = stmt.executeQuery();)
    { 
      ArrayList<Lesson> list = new ArrayList<>(); 

      while(rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lno"));
        lesson.setTitle(rs.getString("title"));
        lesson.setContents(rs.getString("cont"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHours(rs.getInt("tot_hr"));
        lesson.setDayHours(rs.getInt("day_hr"));

        list.add(lesson);
      }
      return list;
    }
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    
    DriverManager.registerDriver(new Driver());
    try(
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/studydb",
            "study" ,"1111");
        PreparedStatement stmt = con.prepareStatement("insert into lesson("
            + "title, cont, sdt, edt, tot_hr, day_hr, mno) "
            + "values(?,?,?,?,?,?,?)" );) {

      stmt.setString(1, lesson.getTitle());
      stmt.setString(2, lesson.getContents());
      stmt.setDate(3, lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHours());
      stmt.setInt(6, lesson.getDayHours());
      stmt.setInt(7, 1);

      return stmt.executeUpdate();
    } 
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    
    Connection con = null;;
    PreparedStatement stmt =null;;

    try {
      DriverManager.registerDriver(new Driver());
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb",
          "study" ,"1111");
      stmt = con.prepareStatement("update lesson set cont=? where lno=?");
      
      stmt.setString(1,lesson.getContents());
      stmt.setInt(2, lesson.getNo());

      return stmt.executeUpdate();

    }finally {
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }
  }

  @Override
  public int delete(int no) throws Exception {
    
    Connection con = null;;
    PreparedStatement stmt = null;;

    try {
      DriverManager.registerDriver(new Driver());
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/studydb",
          "study" ,"1111");
      stmt = con.prepareStatement("delete from lesson where lno=?");
      stmt.setInt(1, no);
      return stmt.executeUpdate();

    }finally {
      try {stmt.close();}catch(Exception e) {}
      try {con.close();}catch(Exception e) {}
    }

  }

}
