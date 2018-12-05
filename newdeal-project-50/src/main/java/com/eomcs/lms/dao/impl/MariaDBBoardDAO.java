package com.eomcs.lms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.BoardDAO;
import com.eomcs.lms.domain.Board;

//Spring IoC Container에게 이 클래스의 인스턴스를 자동 생성하도록 클래스에 표시한다.
@Component
public class MariaDBBoardDAO implements BoardDAO{

  SqlSessionFactory sqlSessionFactory;

  public MariaDBBoardDAO(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public List<Board> findAll() throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectList("BoardDAO.findAll");
    }
  }

  public Board findByNo(int no) throws Exception{
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectOne("BoardDAO.findByNo", no);
    }
  }

  public int insert(Board board) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.insert("BoardDAO.insert", board);

      sqlSession.commit();
      return count;
    }
  }

  public int update(Board board) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.update("BoardDAO.update", board);
      sqlSession.commit();
      return count;
    }
  }

  public int delete(int no) throws Exception{
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      int count = sqlSession.delete("BoardDAO.delete", no);
      sqlSession.commit();
      return count;
    }
  }
}

