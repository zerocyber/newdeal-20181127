package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;

public class LessonAddCommand implements Command{

  Scanner keyboard;
  LessonDAO lessonDAO;

  public LessonAddCommand(Scanner keyboard, LessonDAO lessonDAO) {
    this.keyboard = keyboard;
    this.lessonDAO = lessonDAO;
  }

  public void excute() {
       
    try {
      Lesson lesson = new Lesson();
      
      System.out.print("수강명? ");
      lesson.setTitle(keyboard.nextLine());
      
      System.out.print("수강내용? ");
      lesson.setContents(keyboard.nextLine());
      
      System.out.print("시작일? ");
      lesson.setStartDate(Date.valueOf(keyboard.nextLine()));
      System.out.print("종료일? ");
      lesson.setEndDate(Date.valueOf(keyboard.nextLine()));
      
      System.out.print("총수업시간? ");
      lesson.setTotalHours(Integer.parseInt(keyboard.nextLine()));
      
      System.out.print("일수업시간? ");
      lesson.setDayHours(Integer.parseInt(keyboard.nextLine()));
      
      lessonDAO.insert(lesson);
      System.out.println("입력했습니다!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
