package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.domain.Member;

public class MemberListCommand implements Command{

  Scanner keyboard;
  MemberDAO memberDAO;

  public MemberListCommand(Scanner keyboard , MemberDAO memberDAO) {
    this.keyboard = keyboard;
    this.memberDAO = memberDAO;
  }

  public void excute() {

    try {
      List<Member> list = memberDAO.findAll();

      for(Member member : list) {
        System.out.printf(
            "%3d, %-20s, %s, %s , %s, %s, %s\n",
            member.getNo(),
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getPhoto(),
            member.getTel(),
            member.getRegisteredDate()
            );
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}