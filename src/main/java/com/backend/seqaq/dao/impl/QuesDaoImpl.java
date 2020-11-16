package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.entity.QuestionDetail;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.repository.QuesDetailRepository;
import com.backend.seqaq.repository.QuesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuesDaoImpl implements QuesDao {
  private final QuesRepository quesRepository;
  private final QuesDetailRepository detailRepository;

  public QuesDaoImpl(QuesRepository quesRepository, QuesDetailRepository detailRepository) {
    this.quesRepository = quesRepository;
    this.detailRepository = detailRepository;
  }

  public List<Questions> findByUid(Long uid) {
    return quesRepository.findAllByUid(uid);
  }

  public void save(Questions questions) {
    Questions savedQues = quesRepository.save(questions);
    QuestionDetail detail = savedQues.getDetail();
    detail.setQid(savedQues.getQid());
    detailRepository.save(detail);
  }

  public Questions findById(Long id) {
    Questions question = quesRepository.findById(id).orElse(null);
    return attachDetail(question);
  }

  public List<Questions> findAllByTitleContaining(String text) {
    return quesRepository.findAllByTitleContaining(text);
  }

  public List<Questions> findAllByTagContaining(String text) {
    return quesRepository.findAllByTagContaining(text);
  }

  /**
   * Given qid, fetch the detail for that question.
   *
   * @param qid id of question
   * @return QuestionDetail if qid exists, null otherwise
   */
  private QuestionDetail fetchDetail(Long qid) {
    return detailRepository.findByQid(qid).orElse(null);
  }

  private Questions attachDetail(Questions question) {
    if (question == null) return null;
    QuestionDetail detail = fetchDetail(question.getQid());
    question.setDetail(detail);
    return question;
  }
}
