package com.example.service;

import com.example.message.Message;
import com.example.model.Result;
import com.example.pack.P2PPack;
import com.example.server.task.P2PTask;

public interface MessageService {

   Result P2PMessage(P2PPack pack);
}
