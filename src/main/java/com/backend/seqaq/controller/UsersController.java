package com.backend.seqaq.controller;


import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;


    @PostMapping("/register")
    public String save(@RequestBody Users users) {
        return usersService.Register(users);
    }

    @GetMapping("/findbyid")
    public Users findById(@RequestParam("uid") Long uid){
        return usersService.FindById(uid);
    }

    @GetMapping("/findbyaccount")
    public Users findByAccount(@RequestParam("account") String account){
        return usersService.FindByAccount(account);
    }
}
