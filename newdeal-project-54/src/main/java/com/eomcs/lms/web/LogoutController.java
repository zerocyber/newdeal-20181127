package com.eomcs.lms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

  @RequestMapping("/auth/logout")
  public String excute(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    request.getSession().invalidate();
    
    return "redirect:../auth/form";
  }
}
