package com.eomcs.lms.temp;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;

@WebServlet("/lesson/update")
public class LessonUpdateServlet extends HttpServlet{
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    try {
      Lesson lesson = new Lesson();
      lesson.setNo(Integer.parseInt(request.getParameter("no")));
      lesson.setContents(request.getParameter("contents"));
      
      System.out.println(lesson.getContents());

      lessonDAO.update(lesson);

      // 데이터를 변경한 후 웹 브라우저에게 목록 URL을 다시 요청하라고 응답한다.
      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }
  }
  
  
}
