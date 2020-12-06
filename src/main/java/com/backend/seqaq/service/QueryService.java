package com.backend.seqaq.service;

import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;

import java.util.List;

public interface QueryService {
  List<Questions> queryForQuesByTag(String text);

  List<Questions> queryForQuesByTitle(String text);
  List<Questions> queryForQuesByContent(String text);
  // List<String> getSubString(String text);
  List<Users> queryForUsers(String nickname);
}
