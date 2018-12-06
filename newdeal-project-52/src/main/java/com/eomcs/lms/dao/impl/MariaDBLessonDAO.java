package com.eomcs.lms.dao.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.LessonDAO;
import com.eomcs.lms.domain.Lesson;

@Component
public class MariaDBLessonDAO implements LessonDAO{


  SqlSessionFactory sqlSessionFactory;
  
  @Override
  public List<Map<String, Object>> findByParticipantNo(int memberNo) {
    try(SqlSession sqlSession = sqlSessionFactory.openSession();) {
      return sqlSession.selectList("LessonDAO.findByParticipantNo", memberNo);
    }
  }

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
