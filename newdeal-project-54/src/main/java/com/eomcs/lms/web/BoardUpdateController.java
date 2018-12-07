package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

@Controller
public class BoardUpdateController {

  BoardDAO boardDAO;

  public BoardUpdateController(BoardDAO boardDAO) {

    this.boardDAO = boardDAO;
  }

  @RequestMapping("/board/update")
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setContents(request.getParameter("contents"));

    boardDAO.update(board);

    return "redirect:list";

  }
}
