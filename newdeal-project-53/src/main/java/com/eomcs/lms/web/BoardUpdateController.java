package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

@Component("/board/update")
public class BoardUpdateController implements PageController {

  BoardDAO boardDAO;
  
  public BoardUpdateController(BoardDAO boardDAO) {
    
    this.boardDAO = boardDAO;
  }
  
  @Override
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setContents(request.getParameter("contents"));

    boardDAO.update(board);

    return "redirect:list";

  }
}
