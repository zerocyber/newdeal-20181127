package com.eomcs.lms.dao;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Lesson;

public interface LessonDAO {
  List<Map<String, Object>> findByParticipantNo(int memberNo);
  
  Lesson findByNo(int no) throws Exception;

  List<Lesson> findAll() throws Exception;

  int insert(Lesson lesson) throws Exception;

  int update(Lesson lesson) throws Exception;

  int delete(int no) throws Exception;
}

