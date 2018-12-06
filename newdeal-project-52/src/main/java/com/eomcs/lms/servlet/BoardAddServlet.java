package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  BoardDAO boardDAO;
  LessonDAO lessonDAO;

  @Override
  public void init() {
    ApplicationContext iocContainer =
        (ApplicationContext) this.getServletContext().getAttribute("iocContainer");

    this.boardDAO = iocContainer.getBean(BoardDAO.class);
    this.lessonDAO = iocContainer.getBean(LessonDAO.class);
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    Member member = (Member) session.getAttribute("loginUser");
    
    List<Map<String,Object>> lessons = lessonDAO.findByParticipantNo(member.getNo());
    
    request.setAttribute("lessons", lessons);
    
    RequestDispatcher rd = request.getRequestDispatcher("/board/form.jsp");
    rd.forward(request, response);
    
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      
      Board board = new Board();
      board.setContents(request.getParameter("contents"));
      board.setWriterNo(loginUser.getNo());
      board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

      boardDAO.insert(board);
      
      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }
}
