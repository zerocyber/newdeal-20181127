package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

@Component("/board/detail")
public class BoardDetailController implements PageController{

  BoardDAO boardDAO;

  public BoardDetailController(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;

  }

  @Override
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardDAO.findByNo(no);
    request.setAttribute("board", board);

    response.setContentType("text/html;charset=UTF-8");

    return "/board/detail.jsp";

  }

}