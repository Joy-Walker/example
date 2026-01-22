package com.example.controller;

import com.example.vo.Friend;
import com.example.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author :panligang
 * @description :
 * @create :2026-01-22 10:20:00
 */
@RestController
public class SensitiveController {

    @RequestMapping("/sensitive")
    public String sensitive() {
        return "sensitive";
    }

    @RequestMapping("/user")
    public User getUser() {
        User user = new User();
        user.setName("panligang");
        user.setAge(18);
        user.setPhone("12345678901");
        user.setPasswords(Arrays.asList("123456", "1234567890"));
        Friend friend = new Friend();
        friend.setName("tom");
        friend.setPasswords(Arrays.asList("123456", "1234567890"));
        friend.setPhone("12345678901");
        user.setFriend(friend);
        return user;
    }
}
