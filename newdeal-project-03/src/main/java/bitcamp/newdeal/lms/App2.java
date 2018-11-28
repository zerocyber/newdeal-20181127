package bitcamp.newdeal.lms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    final int DEFUALT_SIZE = 10;

    int[] no = new int[DEFUALT_SIZE];
    String[] name = new String[DEFUALT_SIZE];
    String[] email = new String[DEFUALT_SIZE];
    String[] password = new String[DEFUALT_SIZE];
    String[] photo = new String[DEFUALT_SIZE];
    String[] phone = new String[DEFUALT_SIZE];

    Date today = new Date();
    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");

    int len = 0;

    for(int i = 0; i < DEFUALT_SIZE; i++) {
      System.out.printf("번호?");
      no[i] = Integer.parseInt(scanner.nextLine());

      System.out.printf("이름?");
      name[i] = scanner.nextLine();

      System.out.printf("이메일?");
      email[i] = scanner.nextLine();

      System.out.printf("암호?");
      password[i] = scanner.nextLine();
      System.out.printf("사진?");
      photo[i] = scanner.nextLine();

      System.out.printf("전화번호?");
      phone[i] = scanner.nextLine();

      len++;

      System.out.printf("계속하시겠습니까?(Y/n)");
      String input = scanner.nextLine();
      if(input.equals("") || input.equalsIgnoreCase("y")) {
        continue;
      }
      break;
    }

    scanner.close();

    for(int i = 0; i <len; i++) {
      System.out.printf("번호: %d, 이름: %s, 이메일: %s, 암호: %s, 사진: %s, 전화번호: %s, 작성날짜: %s\n"
          , no[i],name[i],email[i],password[i],photo[i],phone[i],date.format(today));
    }
  }
}
