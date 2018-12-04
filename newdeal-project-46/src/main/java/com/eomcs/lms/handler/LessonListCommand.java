package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;

public class LessonListCommand implements Command{

  Scanner keyboard;
  LessonDAO lessonDAO;

  public LessonListCommand(Scanner keyboard, LessonDAO lessonDAO) {
    this.keyboard = keyboard;
    this.lessonDAO = lessonDAO;
  }

  public void excute() {
    try {
      List<Lesson> list = lessonDAO.findAll();
      
      for(Lesson lesson : list) {
        System.out.printf("%3d, %-20s, %s, %s, %s, %s, %s\n",
            lesson.getNo(),
            lesson.getTitle(),
            lesson.getContents(),
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getTotalHours(),
            lesson.getDayHours()
            ); 
      }
      
    } catch (Exception e) {
      
    }

  }
}
