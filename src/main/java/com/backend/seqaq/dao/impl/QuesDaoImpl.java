package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.entity.QuestionDetail;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.repository.QuesDetailRepository;
import com.backend.seqaq.repository.QuesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuesDaoImpl implements QuesDao {
  private final QuesRepository quesRepository;
  private final QuesDetailRepository detailRepository;

  public QuesDaoImpl(QuesRepository quesRepository, QuesDetailRepository detailRepository) {
    this.quesRepository = quesRepository;
    this.detailRepository = detailRepository;
  }

  public List<Questions> findByUid(Long uid) {
    List<Questions> l = quesRepository.findAllByUid(uid);
    l.forEach(this::attachDetail);
    return l;
  }

  public Long save(Questions questions) {
    Questions savedQues = quesRepository.save(questions);
    QuestionDetail detail = questions.getDetail();
    detail.setQid(savedQues.getQid());
    return detailRepository.save(detail).getQid();
  }

  public Questions findById(Long id) {
    Questions question = quesRepository.findById(id).orElse(null);
    return attachDetail(question);
  }

  public List<Questions> findAllByTitleContaining(String text) {
    return quesRepository.findAllByTitleContaining(text).stream()
        .map(this::attachDetail)
        .collect(Collectors.toList());
  }
  public Page<Questions> findAll(Pageable pageable)
  {
    return quesRepository.findAll(pageable);
  }
  public List<Questions> findAllByTagContaining(String text) {
    return quesRepository.findAllByTagContaining(text).stream()
        .map(this::attachDetail)
        .collect(Collectors.toList());
  }

  @Override
  public List<Questions> findAllByDetailContaining(String text) {
    List<Long> idList =
        detailRepository.findIdByDetailContaining(text).stream()
            .map(QuestionDetail::getQid)
            .collect(Collectors.toList());
    List<Questions> questions = quesRepository.findByQidIn(idList);
    return questions.stream().map(this::attachDetail).collect(Collectors.toList());
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
