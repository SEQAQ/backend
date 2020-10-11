package com.backend.seqaq.controller;


import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api
public class UsersController {
    @Autowired
    private UsersService usersService;


    @PostMapping("/register")
    public String save(@RequestBody Users users) {
        return usersService.register(users);
    }

    @GetMapping("/findbyid")
    public Users findById(@RequestParam("uid") Long uid){
        System.out.println(usersService.findById(uid));
        return usersService.findById(uid);
    }

    @GetMapping("/findbyaccount")
    public Users findByAccount(@RequestParam("account") String account){
        return usersService.findByAccount(account);
    }
}
