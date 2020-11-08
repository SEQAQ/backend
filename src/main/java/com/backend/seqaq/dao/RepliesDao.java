package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Replies;

import java.util.List;

public interface RepliesDao {
    void reply(Replies replies);
    Replies findReply(Long rid);//find a reply
    List<Replies> findByUid(Long uid);
    List<Replies> findByAid(Long aid);
    List<Replies> findByRid(Long rid);//find the replies for the 'rid' reply
}
