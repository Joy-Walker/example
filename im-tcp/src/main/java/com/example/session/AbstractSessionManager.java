package com.example.session;

import com.example.pack.LoginPack;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSessionManager implements SessionManager{

    protected List<SerssionListener> listeners = new ArrayList<>();


    @Override
    public void addSession(String sessionId, LoginPack loginPack) {
        doAddSession(sessionId,loginPack);
        for (SerssionListener listener : listeners) {
            listener.onLogin(sessionId);

        }
    }

    protected abstract void doAddSession(String sessionId, LoginPack loginPack);
    protected abstract void doRemoveSession(String sessionId);


    @Override
    public void removeSession(String sessionId) {
        doRemoveSession(sessionId);
        for (SerssionListener listener : listeners) {
            listener.onLogout(sessionId);
        }
    }

    public void addSessionListener(SerssionListener listener) {
        listeners.add(listener);
    }

    public void removeSessionListener(SerssionListener listener) {
        listeners.remove(listener);
    }
}
