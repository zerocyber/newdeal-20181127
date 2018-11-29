import java.sql.Date;

//새 데이터 타입
//새로운 종류의 메모리를 구성하는 설계도 -> class
public class Lesson {
  int no;
  String title; //실제로는 주소(reference)가 저장, 일반적으론 문자열이 저장된다고 말한다.
  String contents;
  Date startDate;
  Date endDate;
  int totalHours;
  int dayHours;
}
