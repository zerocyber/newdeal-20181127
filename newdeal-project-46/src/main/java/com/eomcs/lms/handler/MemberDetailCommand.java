package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.domain.Member;

public class MemberDetailCommand implements Command{

  Scanner keyboard;
  MemberDAO memberDAO;
  public MemberDetailCommand(Scanner keyboard, MemberDAO memberDAO) {
    this.keyboard = keyboard;
    this.memberDAO = memberDAO;
  }

  public void excute() {
    try {
      System.out.print("번호? ");
      int no = Integer.parseInt(keyboard.nextLine());

      Member member = memberDAO.findByNo(no);

      if(member != null) {
        System.out.printf("번호: %d\n",member.getNo());
        System.out.printf("이름: %s\n",member.getName());
        System.out.printf("이메일: %s\n",member.getEmail());
        System.out.printf("패스워드: %s\n",member.getPassword());
        System.out.printf("사진: %s\n",member.getPhoto());
        System.out.printf("전화번호: %s\n",member.getTel());
        System.out.printf("등록일: %s\n",member.getRegisteredDate());
      }else
        System.out.println("해당 번호의 회원이 존재하지 않습니다.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}