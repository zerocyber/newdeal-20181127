package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.MemberDAO;

public class MemberDeleteCommand implements Command{

  Scanner keyboard;
  MemberDAO memberDAO;

  public MemberDeleteCommand(Scanner keyboard, MemberDAO memberDAO) {
    this.keyboard = keyboard;
    this.memberDAO = memberDAO;
  }

  public void excute() {
    System.out.print("번호?");
    int no = Integer.parseInt(keyboard.nextLine());
    
    try {
      if(memberDAO.delete(no) > 0) {
        System.out.println("삭제되었습니다!");
      }else
        System.out.println("해당 회원이 존재하지 않습니다.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}