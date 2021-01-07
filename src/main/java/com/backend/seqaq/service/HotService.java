package com.backend.seqaq.service;

import com.backend.seqaq.entity.Questions;

import java.util.List;

public interface HotService {
    void add(Long qid,Long uid);
    List<Questions> getTop10(int option);
}
