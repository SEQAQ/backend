package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.FollowersDao;
import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.repository.HotRepository;
import com.backend.seqaq.service.QuesService;
import com.backend.seqaq.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired private FollowersDao followersDao;
    @Autowired private UsersDao usersDao;
    @Autowired private HotRepository hotRepository;
    @Autowired private QuesDao quesDao;

    public List<Users> recommendByfriends(Long uid){
        List<Long> list = followersDao.recommend(uid);
        List<Users> u = new ArrayList<>();
        for (int i = 0; i <list.size();++i)
        {
            u.add(i,usersDao.findById(list.get(i)));
        }
        return u;
    }

    public List<Users> recommendByQues(Long uid){
        List<Long> list = hotRepository.recommend(uid);
        List<Users> u = new ArrayList<>();
        for (int i = 0; i <list.size();++i)
        {
            u.add(i,usersDao.findById(list.get(i)));
        }
        return u;
    }

    public List<Questions> recommendQues(Long uid){
        List<Long> list = followersDao.recommendQues(uid);
        List<Questions> q = new ArrayList<>();
        for (int i = 0; i <list.size();++i)
        {
            q.add(i,quesDao.findById(list.get(i)));
        }
        return q;
    }
}
