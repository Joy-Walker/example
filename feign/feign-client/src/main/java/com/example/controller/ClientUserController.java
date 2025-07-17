package com.example.controller;

import com.example.client.UserClient;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

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

    @RequestMapping(path = "/hi2", method = RequestMethod.GET)
    public String hi2(@RequestParam("name") String name){
        return name;
    };

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFilename);
        return originalFilename + "--" + extension;
    }
}
