package com.example.session.connect;

import com.example.pack.LoginPack;
import com.example.pack.LogoutPack;
import com.example.session.SessionRegistry;
import com.example.utils.RemotingUtil;
import io.netty.channel.Channel;
import io.netty.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.example.constant.Constants.*;

/**
 * @author :panligang
 * @description :
 * @create :2024-04-20 13:12:00
 */
@Component
public class ConnectionHolder {

    public static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHolder.class);

    private  final Map<String, io.netty.channel.Channel> connectionMap = new ConcurrentHashMap<>();

    private final List<ConnectionListener> connectionListeners = new CopyOnWriteArrayList<>();

    @Value("${im.server.port:9090}")
    private String port;


    @Autowired
    private SessionRegistry sessionRegistry;


    private final ConnectionListener connectionListener = event -> {
        switch (event.getEvent()) {
            case CONNECT:
                // 维护用户和机器的关系
                sessionRegistry.register(event.getBasepack().getUserId(), TOPIC_PREFIX + port);
                // attr是channel的属性，每个client都有自己的channel因此是线程安全的.一个woker线程管理多个channel
                event.getChannel().attr(LOGIN_USER).set((LoginPack)event.getBasepack());
                event.getChannel().attr(LAST_TIME).set(System.currentTimeMillis());
                LOGGER.info("新连接建立成功,userId:{},remote:{}", event.getBasepack().getUserId(), RemotingUtil.socketAddress2String(event.getChannel().remoteAddress()));
                break;
            case DIS_CONNECT:
                sessionRegistry.remove(event.getBasepack().getUserId());
                event.getChannel().attr(LOGIN_USER).set(null);
                event.getChannel().attr(LAST_TIME).set(System.currentTimeMillis());
                RemotingUtil.closeChannel(event.getChannel());
                LOGGER.info("连接断开成功,userId:{},remote:{}", event.getBasepack().getUserId(), RemotingUtil.socketAddress2String(event.getChannel().remoteAddress()));
                break;
            default:
                break;
        }
    };


    public ConnectionHolder() {
        addConnectionListener(connectionListener);
    }


    public  void addConnection(LoginPack loginPack, Channel channel) {
        // 维护用户id和channel的映射，将来消息推送使用
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
