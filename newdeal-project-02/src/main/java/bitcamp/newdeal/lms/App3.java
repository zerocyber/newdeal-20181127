package bitcamp.newdeal.lms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    
    System.out.printf("번호?");
    int no = Integer.parseInt(scanner.nextLine());
    
    System.out.printf("내용?");
    String contents = scanner.nextLine();
    
    scanner.close();
    
    int viewCount = 0;
    Date toDay = new Date();
    
    SimpleDateFormat date= new SimpleDateFormat("yyyy/MM/dd");
    
    System.out.printf("번호: %d\n", no);
    System.out.printf("내용: %s\n", contents);
    System.out.printf("작성일: %s\n", date.format(toDay));
    System.out.printf("조회수: %d\n", viewCount);
  }

}
