package com.eomcs.lms.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

// 이 클래스의 객체는 Spring IoC 컨테이너가 관리하도록 설정한다.
@Controller
public class BoardListController{
  BoardDAO boardDAO;

  public BoardListController(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  @RequestMapping("/board/list")
  public String list(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    List<Board> list = boardDAO.findAll();
    request.setAttribute("list", list );
    response.setContentType("text/html;charset=UTF-8");


    return "/board/list.jsp";
  }
}
