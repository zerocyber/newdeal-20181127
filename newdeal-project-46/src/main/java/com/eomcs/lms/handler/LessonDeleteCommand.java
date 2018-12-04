package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.LessonDAO;

public class LessonDeleteCommand implements Command{

  Scanner keyboard;
  LessonDAO lessonDAO;

  public LessonDeleteCommand(Scanner keyboard, LessonDAO lessonDAO) {
    this.keyboard = keyboard;
    this.lessonDAO = lessonDAO;
  }

  public void excute() {
    
    try {
      System.out.println("번호?");
      int no = Integer.parseInt(keyboard.nextLine());
      
      if(lessonDAO.delete(no) > 0) {
        System.out.println("삭제되었습니다!");
      }else
        System.out.println("해당 수업이 없습니다");
      
    } catch (Exception e) {
    }
    
  }
}
