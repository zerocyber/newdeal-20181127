package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDAO;

@Component("/board/delete")
public class BoardDeleteController implements PageController{
  BoardDAO boardDAO;
  
  public BoardDeleteController(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  @Override
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    request.setAttribute("count", boardDAO.delete(no));

    return "redirect:list";

  }
}




