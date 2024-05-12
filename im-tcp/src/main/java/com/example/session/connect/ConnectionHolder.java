package com.example.session.connect;

import com.example.pack.LoginPack;
import com.example.pack.LogoutPack;
import com.example.session.SessionManager;
import com.example.utils.RemotingUtil;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.example.constant.Constants.LAST_TIME;
import static com.example.constant.Constants.LOGIN_USER;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-20 13:12:00
 */
@Component
public class ConnectionHolder {

    private  final Map<String, io.netty.channel.Channel> connectionMap = new ConcurrentHashMap<>();

    private final List<ConnectionListener> connectionListeners = new CopyOnWriteArrayList<>();


    @Autowired
    private SessionManager sessionManager;


    ConnectionListener connectionListener = event -> {
        switch (event.getEvent()) {
            case CONNECT:
                sessionManager.addSession(event.getBasepack().getUserId(), (LoginPack) event.getBasepack());
                // attr是channel的属性，每个client都有自己的channel因此是线程安全的.一个woker线程管理多个channel
                event.getChannel().attr(LOGIN_USER).set((LoginPack)event.getBasepack());
                event.getChannel().attr(LAST_TIME).set(System.currentTimeMillis());
                break;
            case DIS_CONNECT:
                sessionManager.removeSession(event.getBasepack().getUserId());
                event.getChannel().attr(LOGIN_USER).set(null);
                event.getChannel().attr(LAST_TIME).set(System.currentTimeMillis());
                RemotingUtil.closeChannel(event.getChannel());
                break;
            default:
                break;
        }
    };


    public ConnectionHolder() {
        addConnectionListener(connectionListener);
    }


    public  void addConnection(LoginPack loginPack, Channel channel) {
        connectionMap.put(loginPack.getUserId(), channel);
        connectionListeners.forEach(listener -> listener.onEvent(new ConnectEvent(channel,loginPack,ConnectEventEnum.CONNECT)));
    }

    public  Channel getConnection(String userId) {
        return connectionMap.get(userId);
    }

    public void removeConnection(LogoutPack logoutPack) {
        Channel channel = connectionMap.remove(logoutPack.getUserId());
        connectionListeners.forEach(listener -> listener.onEvent(new ConnectEvent(channel,logoutPack,ConnectEventEnum.CONNECT)));
    }

    public void addConnectionListener(ConnectionListener listener) {
        connectionListeners.add(listener);
    }

    public void removeConnectionListener(ConnectionListener listener) {
        connectionListeners.remove(listener);
    }
}
