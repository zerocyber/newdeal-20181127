package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand implements Command{

  Scanner keyboard;
  List<Board> list;
  
  public BoardUpdateCommand(Scanner keyboard, List<Board> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void excute() {
      System.out.print("번호? ");
      int no = Integer.parseInt(keyboard.nextLine());

      int index = indexOfBoard(no);
      if (index == -1) {
        System.out.println("해당 게시글을 찾을 수 없습니다.");
        return;
      }
      
      Board board = list.get(index);
      
      try {
        // 기존 값 복제
        Board temp = board.clone();
        
        System.out.printf("내용? ");
        String input = keyboard.nextLine();
        if (input.length() > 0) 
          temp.setContents(input);
        
        list.set(index, temp);
        
        System.out.println("게시글을 변경했습니다.");
        
      } catch (Exception e) {
        System.out.println("변경 중 오류 발생!");
      }
    }
  
  private int indexOfBoard(int no) {
    for (int i = 0; i < list.size(); i++) {
      Board b = list.get(i);
      if (b.getNo() == no)
        return i;
    }
    return -1;
  }
  
  public void addBoard() {
    Board board = new Board();
    
    System.out.print("번호? ");
    board.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("내용? ");
    board.setContents(keyboard.nextLine());
    
    board.setCreatedDate(new Date(System.currentTimeMillis())); 
    
    board.setViewCount(0);
    
    list.add(board);
    
    System.out.println("저장하였습니다.");
  }
  
}
