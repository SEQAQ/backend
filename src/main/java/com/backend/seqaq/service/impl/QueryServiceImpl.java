package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QueryService;
import com.backend.seqaq.tools.fenci.jieba.JiebaSegmenter;
import com.backend.seqaq.tools.fenci.jieba.SegToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {
  @Autowired private QuesDao quesDao;
  @Autowired private UsersDao usersDao;
  private JiebaSegmenter segmenter = new JiebaSegmenter();

  public List<String> fenci(String test) {
    List<SegToken> tokens = segmenter.process(test, JiebaSegmenter.SegMode.INDEX);
    List<String> result = new ArrayList<String>();
    for (int i = 0; i < tokens.size(); ++i) {
      System.out.println(tokens.get(i).toString());
      result.add(i, tokens.get(i).toString());
    }
    System.out.println(result);
    return result;
  }

  public List<Questions> queryForQuesByTag(String text) {
    List<String> tmp = fenci(text);
    List<Questions> result = new ArrayList<Questions>();
    for (int i = 0; i < tmp.size(); ++i) {
      List<Questions> tmpques = quesDao.findAllByTagContaining(tmp.get(i));
      result.addAll(tmpques);
    }
    List<Questions> fresult = new ArrayList<Questions>();
    for (Questions cd : result) {
      if (!fresult.contains(cd)) {
        fresult.add(cd);
      }
    }
    return fresult;
  }
  public List<Questions> queryForQuesByContent(String text) {
    List<String> tmp = fenci(text);
    List<Questions> result = new ArrayList<Questions>();
    for (int i = 0; i < tmp.size(); ++i) {
      List<Questions> tmpques = quesDao.findAllByDetailContaining(tmp.get(i));
      result.addAll(tmpques);
    }
    List<Questions> fresult = new ArrayList<Questions>();
    for (Questions cd : result) {
      if (!fresult.contains(cd)) {
        fresult.add(cd);
      }
    }
    return fresult;
  }
  public List<Questions> queryForQuesByTitle(String text) {
    List<String> tmp = fenci(text);
    List<Questions> result = new ArrayList<Questions>();
    for (int i = 0; i < tmp.size(); ++i) {
      List<Questions> tmpques = quesDao.findAllByTitleContaining(tmp.get(i));
      result.addAll(tmpques);
    }
    List<Questions> fresult = new ArrayList<Questions>();
    for (Questions cd : result) {
      if (!fresult.contains(cd)) {
        fresult.add(cd);
      }
    }
    return fresult;
  }

  public List<Users> queryForUsers(String nickname) {
    return usersDao.findAllByUnameContaining(nickname);
  }
}
