package com.example.controller;

import com.example.api.UserApi;
import com.example.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-22 22:10:00
 */

@RestController
@RequestMapping("aaa")
public class UserController implements UserApi {

    @Override
    public User findById(Long id) {
        System.out.println("find user by id: " + id);
        return new User("hello");
    }

}
