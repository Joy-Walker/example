package com.example.controller;

import com.example.model.Result;
import com.example.pack.P2PPack;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-24 19:52:00
 */
@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @RequestMapping(method = RequestMethod.POST,value = "/p2p")
    public Result sendMessage(@RequestBody P2PPack pack) {
        return messageService.P2PMessage(pack);
    }
}
