package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    private QuesDao quesDao;
    @Autowired
    private UsersDao usersDao;
    public List<Questions> queryForQuesByTag(String text){
        return quesDao.findAllByTagContaining(text);
    }
    public List<Questions> queryForQuesByTitle(String text){
        return quesDao.findAllByTitleContaining(text);
    }
    public List<Users> queryForUsers(String nickname){
        return usersDao.findAllByUnameContaining(nickname);
    }
}
