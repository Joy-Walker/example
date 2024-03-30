package com.example.api;

import com.example.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



public interface UserApi {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
