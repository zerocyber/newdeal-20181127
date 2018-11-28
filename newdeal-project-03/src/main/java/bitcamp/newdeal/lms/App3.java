package bitcamp.newdeal.lms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int DEFUALT_SIZE = 5;

    int[] no = new int[DEFUALT_SIZE];
    String[] contents = new String[DEFUALT_SIZE];

    Date today = new Date();
    SimpleDateFormat date = new SimpleDateFormat("yy/MM/dd");

    int len = 0;

    for(int i = 0; i < DEFUALT_SIZE; i++) {
      System.out.printf("번호?");
      no[i] = Integer.parseInt(scanner.nextLine());
      System.out.printf("내용?");
      contents[i] = scanner.nextLine();

      len++;

      System.out.printf("계속하시겠습니까?(Y/n)");
      String input = scanner.nextLine();

      if(input.equals("") || input.equalsIgnoreCase("y")) {
        continue;
      }
      break;
    }
    scanner.close();

    int viewCount = 0;

    for(int i = 0; i < len; i++) {
      System.out.printf("번호: %d, 내용: %s     , 작성일: %s, 조회수: %d\n", 
          no[i], contents[i], date.format(today), viewCount);
    }
  }
}
