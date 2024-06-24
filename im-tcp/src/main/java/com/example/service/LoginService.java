package com.example.service;

import com.example.model.Result;
import com.example.pack.LoginPack;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author :panligang
 * @description :
 * @create :2024-06-24 19:43:00
 */
public interface LoginService {

     Result login(LoginPack loginPack, ChannelHandlerContext ctx);
}
