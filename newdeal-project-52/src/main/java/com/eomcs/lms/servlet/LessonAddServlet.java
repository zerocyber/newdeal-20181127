package com.eomcs.lms.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

@WebServlet("/lesson/add")
public class LessonAddServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  LessonDAO lessonDAO;
  ApplicationContext iocContainer;

  @Override
  public void init() throws ServletException {
    ServletContext sc = this.getServletContext();
    iocContainer = 
        (ApplicationContext)sc.getAttribute("iocContainer");
    try {
      lessonDAO = iocContainer.getBean(LessonDAO.class);
    }catch(Exception e){
      e.printStackTrace();
    }

  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    RequestDispatcher rd = request.getRequestDispatcher("/lesson/form.jsp");
    rd.forward(request, response);
    
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      
      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
      lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));
      lesson.setMemberNo((loginUser.getNo()));
      
      lessonDAO.insert(lesson);
      
      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }


}
