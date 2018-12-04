package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand implements Command{

  Scanner keyboard;
  MemberDAO memberDAO;

  public MemberUpdateCommand(Scanner keyboard, MemberDAO memberDAO) {
    this.keyboard = keyboard;
    this.memberDAO = memberDAO;
  }

  public void excute() {
    try {
      System.out.print("번호? ");
      int no = Integer.parseInt(keyboard.nextLine());
      
      Member member = memberDAO.findByNo(no);
      
      System.out.printf("무슨 이름으로 바꾸시겠습니까? (이전 이름 : %s)", member.getName());
      member.setName(keyboard.nextLine());
      
      memberDAO.update(member);
      System.out.println("변경했습니다!");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
  }

}