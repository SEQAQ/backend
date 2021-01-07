package com.backend.seqaq.service;

import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;

import java.util.List;

public interface RecommendService {
    List<Users> recommendByfriends(Long uid);
    List<Users> recommendByQues(Long uid);
    List<Questions> recommendQues(Long uid);

}
