package com.zhuzichu.blog.core.service.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketService {

    //统计在线人数
    private static int onlineCount = 0;

    //用本地线程保存session
    private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();

    //保存所有连接上的session
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }

    //连接
    @OnOpen
    public void onOpen(Session session) {
        sessions.set(session);
        addOnlineCount();
        sessionMap.put(session.getId(), session);
        System.out.println("【" + session.getId() + "】连接上服务器======当前在线人数【" + getOnlineCount() + "】");
        //连接上后给客户端一个消息
        sendMsg(session, "连接服务器成功！");
    }

    //关闭
    @OnClose
    public void onClose(Session session) {
        subOnlineCount();
        sessionMap.remove(session.getId());
        System.out.println("【" + session.getId() + "】退出了连接======当前在线人数【" + getOnlineCount() + "】");
    }

    //接收消息   客户端发送过来的消息
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("【" + session.getId() + "】客户端的发送消息======内容【" + message + "】");
        String[] split = message.split(",");
        String sessionId = split[0];
        Session ss = sessionMap.get(sessionId);
        if (ss != null) {
            String msgTo = "【" + session.getId() + "】发送给【您】的消息:\n【" + split[1] + "】";
            String msgMe = "【我】发送消息给【"+ss.getId()+"】:\n"+split[1];
            sendMsg(ss, msgTo);
            sendMsg(session,msgMe);
        }else {
            for (Session s : sessionMap.values()) {
                if (!s.getId().equals(session.getId())) {
                    sendMsg(s, "【" + session.getId() + "】发送给【您】的广播消息:\n【" + message + "】");
                } else {
                    sendMsg(session,"【我】发送广播消息给大家\n"+message);
                }
            }
        }

    }

    //异常
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("发生异常!");
        throwable.printStackTrace();
    }



    //统一的发送消息方法
    public synchronized void sendMsg(Session session, String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendAll(String msg){
        sessionMap.forEach((key, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}