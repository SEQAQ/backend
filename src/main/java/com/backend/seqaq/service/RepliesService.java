package com.backend.seqaq.service;

import com.backend.seqaq.entity.Replies;

import java.util.List;

public interface RepliesService {
  String replyAnswers(Long uid, Long did, String text);

  String replyReplies(Long uid, Long did, String text);

  Replies findReply(Long rid); // find a reply

  List<Replies> findByUid(Long uid);

  List<Replies> findByAid(Long aid);

  List<Replies> findByRid(Long rid); // find the replies for the 'rid' reply

  String likeReplies(Long rid);

  String dislikeReplies(Long rid);
}
