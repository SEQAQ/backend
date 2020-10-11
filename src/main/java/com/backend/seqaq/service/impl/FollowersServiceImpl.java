package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.FollowersDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Followers;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.FollowersService;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowersServiceImpl implements FollowersService {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private FollowersDao followersDao;


    public List<Followers> findAllFollowedByUid(Long uid) {
        return followersDao.findAllFollowedByUid(uid);
    }
    public List<Followers> findAllFollowerByUid(Long uid) {
        return followersDao.findAllFollowerByUid(uid);
    }
    public String addFollow(Long uid1,Long uid2){
        Users user1 = usersDao.findById(uid1);
        Users user2 = usersDao.findById(uid2);
        if(user1 == null || user2 == null) return "Error";
        else {
            Followers followers = new Followers();
            followers.setUid1(uid1);
            followers.setUid2(uid2);
            followers.setUsers1(user1);
            followers.setUsers2(user2);
            followersDao.addFollow(followers);
            return "OK";
        }
    }
    public String delFollow(Long uid1,Long uid2){
        Users user1 = usersDao.findById(uid1);
        Users user2 = usersDao.findById(uid2);
        if(user1 == null || user2 == null) return "Error";
        else {
            followersDao.delFollow(uid1,uid2);
            return "OK";
        }
    }
}
