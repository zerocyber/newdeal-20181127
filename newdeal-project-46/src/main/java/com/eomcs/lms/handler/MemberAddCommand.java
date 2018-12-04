package com.eomcs.lms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.domain.Member;

public class MemberAddCommand implements Command{

  Scanner keyboard;
  MemberDAO memberDAO;

  public MemberAddCommand(Scanner keyboard , MemberDAO memberDAO) {
    this.keyboard = keyboard;
    this.memberDAO = memberDAO;
  }

  public void excute() {    
    
    try {
    Member member = new Member();
    
    System.out.print("이름?");
    member.setName((keyboard.nextLine()));
    System.out.print("이메일?");
    member.setEmail(keyboard.nextLine());
    System.out.print("비밀번호?");
    member.setPassword((keyboard.nextLine()));
    System.out.print("사진?");
    member.setPhoto((keyboard.nextLine()));
    System.out.print("전화번호?");
    member.setTel((keyboard.nextLine()));
    
    memberDAO.insert(member);
    System.out.println("입력했습니다~!");
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}