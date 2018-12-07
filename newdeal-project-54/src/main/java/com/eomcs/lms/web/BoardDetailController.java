package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

@Controller
public class BoardDetailController{
  BoardDAO boardDAO;

  public BoardDetailController(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  @RequestMapping("/board/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardDAO.findByNo(no);
    request.setAttribute("board", board);

    response.setContentType("text/html;charset=UTF-8");

    return "/board/detail.jsp";
  }

}