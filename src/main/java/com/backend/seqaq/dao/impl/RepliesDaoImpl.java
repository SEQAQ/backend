package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.RepliesDao;
import com.backend.seqaq.entity.Replies;
import com.backend.seqaq.entity.ReplyContent;
import com.backend.seqaq.repository.RepliesRepository;
import com.backend.seqaq.repository.ReplyContentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepliesDaoImpl implements RepliesDao {
    private final RepliesRepository repliesRepository;
    private final ReplyContentRepository replyContentRepository;

    public RepliesDaoImpl(RepliesRepository repliesRepository,
                          ReplyContentRepository replyContentRepository) {
        this.repliesRepository = repliesRepository;
        this.replyContentRepository = replyContentRepository;
    }

    public void reply(Replies replies) {
        repliesRepository.save(replies);
    }

    public Replies findReply(Long rid) {
        return attachDetail(repliesRepository.findById(rid).orElse(null));
    }

    public List<Replies> findByUid(Long uid) {
        return attachDetail(repliesRepository.findAllByUidAndDtype(uid, 0));
    }

    public List<Replies> findByAid(Long aid) {
        return attachDetail(repliesRepository.findAllByDidAndDtype(aid, 0));
    }

    public List<Replies> findByRid(Long rid) {
        return attachDetail(repliesRepository.findAllByDidAndDtype(rid, 1));
    }

    private ReplyContent fetchDetail(Long rid) {
        return replyContentRepository.findByRid(rid).orElse(null);
    }

    private Replies attachDetail(Replies reply) {
        if (reply == null) return null;
        ReplyContent content = fetchDetail(reply.getRid());
        reply.setContent(content);
        return reply;
    }

    private List<Replies> attachDetail(List<Replies> list) {
        list.forEach(this::attachDetail);
        return list;
    }
}
