package com.eomcs.lms.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Member;

@Component("/board/form")
public class BoardFormController implements PageController{
  
  LessonDAO lessonDAO;

  public BoardFormController(LessonDAO lessonDAO) {
    this.lessonDAO = lessonDAO;
  }
  
  @Override
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    HttpSession session = request.getSession();
    Member member = (Member) session.getAttribute("loginUser");
    
    List<Map<String,Object>> lessons = lessonDAO.findByParticipantNo(member.getNo());
    
    request.setAttribute("lessons", lessons);
    
    response.setContentType("text/html;charset=UTF-8");
    
    return "/board/form.jsp";
  }

}
