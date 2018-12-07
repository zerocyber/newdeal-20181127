package com.eomcs.lms.dao;

import java.util.Map;
import com.eomcs.lms.domain.Member;

public interface MemberDAO {

  Member findByEmailPassword(Map<String, Object> params) throws Exception;
}
