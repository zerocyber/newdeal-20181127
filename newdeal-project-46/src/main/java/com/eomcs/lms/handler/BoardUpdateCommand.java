package com.eomcs.lms.handler;

import java.util.Scanner;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

public class BoardUpdateCommand implements Command{
  Scanner keyboard;
  BoardDAO boardDAO;

  public BoardUpdateCommand(Scanner keyboard,BoardDAO boardDAO) {
    this.keyboard = keyboard;
    this.boardDAO = boardDAO;
  }

  public void excute() {
    try {
      System.out.print("번호? ");
      int no = Integer.parseInt(keyboard.nextLine());

      Board board = boardDAO.findByNo(no);

      System.out.printf("내용?( 이전내용 : %s) " , board.getContents());
      board.setContents(keyboard.nextLine());
      
      boardDAO.update(board);

      System.out.println("변경했습니다!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
