package com.eomcs.lms.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

@Controller
@RequestMapping("/board")
public class BoardController{
  BoardDAO boardDAO;
  LessonDAO lessonDAO;

  public BoardController(BoardDAO boardDAO, LessonDAO lessonDAO) {
    this.boardDAO = boardDAO;
    this.lessonDAO = lessonDAO;
  }

  @RequestMapping("add")
  public String add(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");

    Board board = new Board();
    board.setContents(request.getParameter("contents"));
    board.setWriterNo(loginUser.getNo());
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    boardDAO.insert(board);

    return "redirect:list";
  }
  
  @RequestMapping("delete")
  public String delete(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    request.setAttribute("count", boardDAO.delete(no));

    return "redirect:list";
  }
  
  @RequestMapping("detail")
  public String detail(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = boardDAO.findByNo(no);
    request.setAttribute("board", board);

    response.setContentType("text/html;charset=UTF-8");

    return "/board/detail.jsp";
  }
  
  @RequestMapping("form")
  public String form(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    HttpSession session = request.getSession();
    Member member = (Member) session.getAttribute("loginUser");
    
    List<Map<String,Object>> lessons = lessonDAO.findByParticipantNo(member.getNo());
    
    request.setAttribute("lessons", lessons);
    
    response.setContentType("text/html;charset=UTF-8");
    
    return "/board/form.jsp";
  }
  
  @RequestMapping("list")
  public String list(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List<Board> list = boardDAO.findAll();
    request.setAttribute("list", list );
    response.setContentType("text/html;charset=UTF-8");

    return "/board/list.jsp";
  }
  
  @RequestMapping("update")
  public String update(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setContents(request.getParameter("contents"));

    boardDAO.update(board);

    return "redirect:list";
  }
}
