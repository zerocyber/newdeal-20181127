package bitcamp.newdeal.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
      Scanner keyIn = new Scanner(System.in);
      
      System.out.print("번호?");
      int no = Integer.parseInt(keyIn.nextLine());
      
      System.out.print("수업명?");
      String title = keyIn.nextLine();
      
      System.out.print("내용?");
      String contents = keyIn.nextLine();
      
      System.out.print("강의 시작일?");
      Date startDate = Date.valueOf(keyIn.nextLine());
      
      System.out.print("강의 종료일?");
      Date endDate = Date.valueOf(keyIn.nextLine());
      
      System.out.print("총 강의 시간??");
      int totalHours = Integer.parseInt(keyIn.nextLine());
      
      System.out.print("하루 강의 시간?");
      int dayHours = Integer.parseInt(keyIn.nextLine());
      
      keyIn.close();
      
      System.out.println("번호 : " + no);
      System.out.println("수업명: %s\n" +  title); //문자열 안에 삽입되는 명령어 : 이스케이프 명령어
      System.out.println("내용: %s\n" +  contents);
      System.out.println("시작일: %s\n" + startDate );
      System.out.println("종료일: %s\n" +  endDate);
      System.out.println("총수업시간: %d\n" + totalHours );
      System.out.println("하루 강의 시간 : %d\n" + dayHours);
      
    }
}
