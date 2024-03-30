package com.example.controller;

import com.example.client.UserClient;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :panligang
 * @description :
 * @create :2024-03-22 22:23:00
 */
@RestController
public class ClientUserController {

    @Autowired
    UserClient userClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userClient.findById(id);
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") Long id) {
        return "hello" + id;
    }
}
