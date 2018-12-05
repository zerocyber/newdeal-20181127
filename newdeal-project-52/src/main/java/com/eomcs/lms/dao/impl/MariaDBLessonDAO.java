package com.eomcs.lms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;

public class MariaDBLessonDAO implements LessonDAO{


  SqlSessionFactory sqlSessionFactory;

  public MariaDBLessonDAO(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectList("LessonDAO.findAll");
    }
  }

  @Override
  public Lesson findByNo(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectOne("LessonDAO.findByNo");
    }
  }


  @Override
  public int insert(Lesson lesson) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.insert("LessonDAO.insert");
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.update("LessonDAO.update");
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.delete("LessonDAO.delete");
    }
  }
}
