package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDAO;

@Controller
public class BoardDeleteController{
  BoardDAO boardDAO;
  
  public BoardDeleteController(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  @RequestMapping("/board/delete")
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    request.setAttribute("count", boardDAO.delete(no));

    return "redirect:list";

  }
}




