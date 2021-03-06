package com.eomcs.lms.temp;

import java.io.IOException;
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

@WebServlet("/lesson/detail")
public class LessonDetailServlet extends HttpServlet{
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
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Lesson lesson = lessonDAO.findByNo(no);
      request.setAttribute("lesson", lesson);
      
      RequestDispatcher rd = request.getRequestDispatcher("/lesson/detail.jsp");
      response.setContentType("text/html;charset=UTF-8");
      rd.include(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }


}
