package com.example.session;

import com.example.pack.LoginPack;

public interface SerssionListener {

    public void onLogin(String sessionId);

    public void onLogout(String sessionId);
}
