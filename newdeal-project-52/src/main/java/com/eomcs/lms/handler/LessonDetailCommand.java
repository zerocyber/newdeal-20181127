package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.impl.MariaDBLessonDAO;
import com.eomcs.lms.domain.Lesson;

public class LessonDetailCommand implements Command{

  Scanner keyboard;
  MariaDBLessonDAO lessonDAO;

  public LessonDetailCommand(Scanner keyboard, MariaDBLessonDAO lessonDAO) {
    this.keyboard = keyboard;
    this.lessonDAO = lessonDAO;
  }

  public void excute() {    
    try {
      System.out.print("번호? ");
      int no = Integer.parseInt(keyboard.nextLine());

      Lesson lesson = lessonDAO.findByNo(no);
      System.out.println("no : " + no);
      if(lesson != null) {       
        System.out.printf("번호: %d\n",lesson.getNo());
        System.out.printf("수업명: %s\n",lesson.getTitle());
        System.out.printf("수업내용: %s\n",lesson.getContents());
        System.out.printf("시작일: %s\n",lesson.getStartDate());
        System.out.printf("종료일: %s\n",lesson.getEndDate());
        System.out.printf("총수업시간: %d\n",lesson.getTotalHours());
        System.out.printf("일수업시간: %d\n",lesson.getDayHours());
      }else
        System.out.println("해당 수업이 없습니다.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
