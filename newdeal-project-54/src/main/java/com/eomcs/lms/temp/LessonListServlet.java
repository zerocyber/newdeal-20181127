package com.eomcs.lms.temp;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/lesson/list")
public class LessonListServlet extends HttpServlet{
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
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      List<Lesson> list = lessonDAO.findAll();

      // 게시물 목록을 JSP가 사용할 수 있도록 보관소 저장한다.
      request.setAttribute("list", list );

      // JSP로 실행을 위임한다.
      RequestDispatcher rd = request.getRequestDispatcher("/lesson/list.jsp");

      // 출력 Content의 타입을 include 하는 쪽에서 지정해야 한다.
      response.setContentType("text/html;charset=UTF-8");
      rd.include(request, response);

    }catch (Exception e) {
      e.printStackTrace();
      throw new ServletException(e);
    }

  }
  
}
