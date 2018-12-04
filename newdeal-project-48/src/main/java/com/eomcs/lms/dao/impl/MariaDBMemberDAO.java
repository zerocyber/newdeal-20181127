package com.eomcs.lms.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.mariadb.jdbc.Driver;
import com.eomcs.lms.dao.MemberDAO;
import com.eomcs.lms.domain.Member;

public class MariaDBMemberDAO implements MemberDAO{

  @Override
  public Member findByEmailPassword(String email, String password) throws Exception {

    DriverManager.registerDriver(new Driver());
    try(
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/studydb","study" ,"1111");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select mno,name,email,photo,tel"
                + " from member"
                + " where email='" + email
                + "' and pwd='" + password + "'");
        ) {
      if(rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("mno"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhoto(rs.getString("photo"));
        return member;
      }else {
        return null;
      }
    }
  }

}
