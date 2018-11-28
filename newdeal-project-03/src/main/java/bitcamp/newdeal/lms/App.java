package bitcamp.newdeal.lms;

import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    final int DEFAULT_SIZE = 20; //final => 한번 값을 정하고 나서 밑에서 바꾸는 것을 방지
    int[] no = new int[DEFAULT_SIZE];
    String[] title = new String[DEFAULT_SIZE];
    String[] contents = new String[DEFAULT_SIZE];
    Date[] startDate = new Date[DEFAULT_SIZE];
    Date[] endDate = new Date[DEFAULT_SIZE];
    int[] totalHours = new int[DEFAULT_SIZE];
    int[] dayHours = new int[DEFAULT_SIZE];

    int len = 0;

    Scanner keyIn = new Scanner(System.in);

    for(int i = 0; i < DEFAULT_SIZE; i++) {
      System.out.print("번호?");
      no[i] = Integer.parseInt(keyIn.nextLine());

      System.out.print("수업명?");
      title[i] = keyIn.nextLine();

      System.out.print("내용?");
      contents[i] = keyIn.nextLine();

      System.out.print("강의 시작일?");
      startDate[i] = Date.valueOf(keyIn.nextLine());

      System.out.print("강의 종료일?");
      endDate[i] = Date.valueOf(keyIn.nextLine());

      System.out.print("총 강의 시간??");
      totalHours[i] = Integer.parseInt(keyIn.nextLine());

      System.out.print("하루 강의 시간?");
      dayHours[i] = Integer.parseInt(keyIn.nextLine());

      len++;

      System.out.print("계속 하시겠습니까?(Y/n)");
      String input = keyIn.nextLine();
      if(input.equals("") || input.equalsIgnoreCase("y")) {
        continue;
      }
      break;
    }
    keyIn.close();

    for (int i = 0; i < len; i++) {
      System.out.printf("%d, %s, %s, %s ~ %s, %d, %d\n" ,
          no[i],title[i],contents[i],startDate[i],endDate[i], totalHours[i], dayHours[i]);
    }
  }
}