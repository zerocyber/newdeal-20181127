package com.eomcs.lms.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
  public String add(Board board, HttpSession session) throws Exception {
    Member loginUser = (Member)session.getAttribute("loginUser");

    board.setWriterNo(loginUser.getNo());

    boardDAO.insert(board);

    return "redirect:list";
  }
  
  @RequestMapping("delete")
  public String delete(int no, Model model) throws Exception {
    model.addAttribute("count", boardDAO.delete(no)); // Map에 넣어도 된다.
    return "board/delete";
  }
  
  @RequestMapping("detail")
  public String detail(int no, Model model) throws Exception {  
    Board board = boardDAO.findByNo(no);
    model.addAttribute("board", board);

    return "board/detail";
  }
  
  @RequestMapping("form")
  public String form(HttpSession session, Model model) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");
    
    List<Map<String,Object>> lessons = lessonDAO.findByParticipantNo(member.getNo());
    
    model.addAttribute("lessons", lessons);
    
    return "board/form";
  }
  
  @RequestMapping("list")
  public ModelAndView list(Model model) throws Exception {
    ModelAndView mv = new ModelAndView();
    List<Board> list = boardDAO.findAll();
    mv.addObject("list", list );
    mv.setViewName("board/list");
    
    return mv;
  }
  
  @RequestMapping("update")
  public String update(Board board) throws Exception {
    boardDAO.update(board);

    return "redirect:list";
  }
}
