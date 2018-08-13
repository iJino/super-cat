package com.liangjinhai.supercat.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;


public class ShiroSessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {
        System.out.println(session.getId()+" start...");
    }

    @Override
    public void onStop(Session session) {

        System.out.println(session.getId()+" stop...");
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println(session.getId()+" expired...");
    }
}



