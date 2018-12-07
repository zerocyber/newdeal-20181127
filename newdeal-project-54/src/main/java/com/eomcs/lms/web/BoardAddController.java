package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

@Controller
public class BoardAddController{

  BoardDAO boardDAO;

  public BoardAddController(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  @RequestMapping("/board/add")
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");

    Board board = new Board();
    board.setContents(request.getParameter("contents"));
    board.setWriterNo(loginUser.getNo());
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    boardDAO.insert(board);

    return "redirect:list";

  }
}
