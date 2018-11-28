package bitcamp.newdeal.lms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    
    Scanner scanner =  new Scanner(System.in);
    
    System.out.printf("번호?");
    int no = Integer.parseInt(scanner.nextLine());
    
    System.out.printf("이름?");
    String name = scanner.nextLine();
    
    System.out.printf("이메일?");
    String email = scanner.nextLine();
    
    System.out.printf("암호?");
    String password = scanner.nextLine();
    
    System.out.printf("사진?");
    String photo = scanner.nextLine();
    
    System.out.printf("전화번호?");
    String phone = scanner.nextLine();
    
    scanner.close();
    Date toDay = new Date();
    
    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
    
    System.out.printf("번호: %d\n ", no);
    System.out.printf("이름: %s\n" , name);
    System.out.printf("이메일: %s\n" , email);
    System.out.printf("비밀번호: %s\n" , password);
    System.out.printf("사진: %s\n", photo);
    System.out.printf("전화번호: %s\n" , phone);
    System.out.printf("가입 날짜: %s\n" , date.format(toDay));

  }
}
