package com.offer.util;

import com.offer.pojo.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @Describe socket服务类，
 * @created ipromisemr
 * @time: 2019/9/18 15:59
 **/
@Component
@ServerEndpoint(value = "/socketServer/{userName}")
public class SocketServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    /**
     * 用线程安全的CopyOnWriteArraySet来存放客户端连接的信息
     */
    private static CopyOnWriteArraySet<Client> socketServers = new CopyOnWriteArraySet<>();

    /**
     * websocket封装的session，消息推送
     */
    private Session session;

    private final static String SYS_USERNAME = "pfo2019";

    @OnOpen
    public void open(Session session, @PathParam(value="userName")String userName) {
        this.session = session;
        socketServers.add(new Client(userName, session));

        logger.info("客户端:【{}】连接成功",userName);
    }

    /**
     * 收到客户端信息时触发
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        Client client = socketServers.stream().filter(cli -> cli.getSession() == session)
                .collect(Collectors.toList()).get(0);
        sendMessage(client.getUserName() + "<--" + message, SYS_USERNAME);

        logger.info("客户端:【{}】发送信息:{}",client.getUserName(),message);
    }

    /**
     * 客户端关闭时触发
     */
    @OnClose
    public void onClose() {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {
                logger.info("客户端:【{}】断开连接",client.getUserName());
                socketServers.remove(client);
            }
        });
    }

    /**
     * 客户端发生错误时触发
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        socketServers.forEach(client -> {
            if (client.getSession().getId().equals(session.getId())) {
                logger.error("客户端:【{}】发生异常",client.getUserName());
                error.printStackTrace();
            }
        });
    }

    /**
     * 信息发送的方法，通过客户端的username拿到对应的session，调用信息推送的方法
     * @param message
     * @param userName
     */
    private synchronized static void sendMessage(String message, String userName) {
        // 遍历查找userName，并调用该客户端的信息推送方法
        socketServers.forEach(client -> {
            if (userName.equals(client.getUserName())) {
                try {
                    // 调用发送信息方法
                    client.getSession().getBasicRemote().sendText(message);

                    logger.info("服务端推送给客户端 :【{}】",client.getUserName(),message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取服务端当前客户端的连接数量
     * 服务端本身也作为客户端接收信息，所以将服务端过滤，即连接总数减1
     * @return
     */
    public synchronized static int getOnlineNum() {
        // 返回连接的socketServers集合的大小(即在线人数)，除了服务端本身
        return socketServers.stream().filter(client -> !client.getUserName().equals(SYS_USERNAME))
                .collect(Collectors.toList()).size();
    }

    /**
     * 获取在线用户名，同时排除排除服务端自己
     * @return
     */
    public synchronized static List<String> getOnlineUsers() {
        // 查找所有的在线用户，除了服务端本身
        List<String> onlineUsers = socketServers.stream().filter(client -> !client.getUserName().equals(SYS_USERNAME))
                .map(client -> client.getUserName()).collect(Collectors.toList());

        return onlineUsers;
    }

    /**
     * 消息群发，同时排除服务端自己不接收到推送消息
     * @param message
     */
    public synchronized static void sendAll(String message) {
        // 群发  除了服务端自己
        socketServers.stream().filter(cli -> cli.getUserName() != SYS_USERNAME)
                .forEach(client -> {
                    try {
                        client.getSession().getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        logger.info("服务端推送给所有客户端 :【{}】",message);
    }

    /**
     * 推送给指定用户
     * @param message
     * @param persons
     */
    public synchronized static void sendMany(String message, String[] persons) {
        for (String userName : persons) {
            sendMessage(message, userName);
        }
    }
}
