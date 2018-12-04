package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateCommand implements Command{

  Scanner keyboard;
  LessonDAO lessonDAO;

  public LessonUpdateCommand(Scanner keyboard, LessonDAO lessonDAO) {
    this.keyboard = keyboard;
    this.lessonDAO = lessonDAO;
  }

  public void excute() {
    
    try {
      
      System.out.println("번호?");
      int no = Integer.parseInt(keyboard.nextLine());
      Lesson lesson = lessonDAO.findByNo(no);
      
      System.out.printf("바꿀 내용? (이전내용 : %s)" , lesson.getContents());
      lesson.setContents(keyboard.nextLine());
      
      lessonDAO.update(lesson);
      System.out.println("변경되었습니다!");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

}
