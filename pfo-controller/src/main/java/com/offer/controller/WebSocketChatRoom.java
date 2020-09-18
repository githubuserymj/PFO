package com.offer.controller;


import javax.swing.text.html.parser.Entity;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


import com.offer.pojo.PfoMessage;
import com.offer.service.MessageService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

/**
 * Created by YMJ on 2019-09-26.
 */
@ServerEndpoint("/pfoChat/{roomId}/{userId}")
@Component
public class WebSocketChatRoom {

    private static MessageService messageService;

    @Autowired
    private void setMessageService(MessageService messageService){//使用这种方式才不会出现空指针错误
        WebSocketChatRoom.messageService=messageService;
    }

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建时间格式对象

    private static ConcurrentHashMap<String,ConcurrentHashMap<String,WebSocketChatRoom>> rooms = new ConcurrentHashMap<>();//ConcurrentHashMap<String(roomId房间号：为第一次创建房间的用户id),ConcurrentHashMap<String,WebSocketChatRoom>>

    private Session session;//连接会话

    private HashMap<String,String> messHashMap = new HashMap<>();//消息

    public synchronized void joinRoom(String roomId,String userId,Session session) throws IOException {
        this.session = session;//更新客户端session
        ConcurrentHashMap<String,WebSocketChatRoom> targetRoom = rooms.get(roomId);//获取目标用户创建的房间
        if(null != targetRoom){//目标房间存在则在房间内存入用户客户端session
            targetRoom.put(userId,this);
        }else{//不存在则自己创建新房间
            ConcurrentHashMap<String,WebSocketChatRoom> room = new ConcurrentHashMap<>();//存放socket对象,已登录对象客户端存放处,创建新房间
            room.put(userId,this);
            rooms.put(roomId,room);//把房间放入房间集合里
        }
        System.out.println("=============加入后当前房间信息");
        for(String roomIdKey:rooms.keySet()){
            System.out.println("房号：" + roomIdKey);
            ConcurrentHashMap<String,WebSocketChatRoom> tempChatMap = rooms.get(roomIdKey);
            for(String userIdKey:tempChatMap.keySet()){
                System.out.println("用户标识："+userIdKey+" websocket："+tempChatMap.get(userIdKey)+" 客户端session："+tempChatMap.get(userIdKey).session.toString());
            }
        }
        System.out.println();
    }

    //    发送消息到所有聊天室在线的所有用户
    public void sendMessToAllUser(String mess) throws IOException {
//        System.out.println("messageToAllUser:"+mess);
        for(String roomKey:rooms.keySet()){
            ConcurrentHashMap<String,WebSocketChatRoom> room = rooms.get(roomKey);//根据房号获取房间
            for(String userIdkey:room.keySet()){
                room.get(userIdkey).session.getBasicRemote().sendText(mess);
            }
        }
    }

    //    发送消息到指定聊天室在线的所有用户
    public void sendMessToTargetRoomAllUser(String roomId,String mess) throws IOException {
//        System.out.println("messageToAllUser:"+mess);
        ConcurrentHashMap<String,WebSocketChatRoom> room = rooms.get(roomId);//根据房号获取房间
        if(null != room){
            for(String userIdkey:room.keySet()){
                room.get(userIdkey).session.getBasicRemote().sendText(mess);
            }
        }
    }

    //    发送消息到指定聊天室指定用户
    public void sendMessToTargetUser(String roomId,String targetUserId,HashMap messMap) throws IOException {
        String mess = JSONObject.fromObject(messHashMap).toString();
//        System.out.println("messageToTargetUser:"+mess);
        ConcurrentHashMap<String,WebSocketChatRoom> room = rooms.get(roomId);//根据房号获取房间
        if(null != room){
            WebSocketChatRoom targetUserWebSocket = room.get(targetUserId);//获取目标用户客户端session
            if(null != targetUserWebSocket){//目标用户在room中
//                System.out.println("mess："+mess);
                targetUserWebSocket.session.getBasicRemote().sendText(mess);
            }else{//目标用户不在指定房间则将其数据存入数据库
                PfoMessage message = new PfoMessage();
                message.setUserId(Long.parseLong(messHashMap.get("userId").toString()));
                message.setTargetUserId(Long.parseLong(targetUserId));
                message.setMessageText(messMap.get("chatContent").toString());
                messageService.addMessage(message);
            }
        }
    }

    @OnOpen
    public void onOpen(@PathParam(value = "roomId")String roomId, @PathParam(value = "userId")String userId, Session session) throws IOException {
        System.out.println("用户："+userId+"打开房号："+roomId);
        if(!roomId.equals("null") && !roomId.equals("")&&!userId.equals("null") && !userId.equals("")){
            joinRoom(roomId,userId,session);
        }
    }

    @OnMessage
    public void onMessage(String content) throws IOException {
        JSONObject JSONcontent = JSONObject.fromObject(content);
        String roomId = JSONcontent.get("roomId").toString();
        String state = JSONcontent.get("state").toString();
        String userId = JSONcontent.get("userId").toString();

        if(state.equals("inRoom") && null != roomId){
            String userName = JSONcontent.get("userName").toString();
            String userPhoto = JSONcontent.get("userPhoto").toString();
            String chatContent = JSONcontent.get("chatContent").toString().replace("\n","<br>");
            Object targetUserIdObj = JSONcontent.get("targetUserId");
            String nowDate = df.format(new Date());

            messHashMap.put("userId",userId);
            messHashMap.put("userName",userName);
            messHashMap.put("userPhoto",userPhoto);
            messHashMap.put("chatContent",chatContent);
            messHashMap.put("state",state);
            messHashMap.put("date",nowDate);
            //指明了消息发送的目标用户
            if(null != targetUserIdObj){
                String targetUserId=targetUserIdObj.toString();
                messHashMap.put("targetUserId",targetUserId);
                //并且其不为空
                if(!targetUserId.equals("")){
                    sendMessToTargetUser(roomId,targetUserId,messHashMap);
                }
            }else{
//                sendMessToAllUser(JSONObject.fromObject(messHashMap).toString());
            }
        }
    }

    /**
     * 用户链接断开
     */
    @OnClose
    public void onClose(){
//        System.out.println(this.session.toString());
//        chatRoomMap.remove(this);
        for(String roomIdKey:rooms.keySet()){
            ConcurrentHashMap<String,WebSocketChatRoom> chatRoomMap = rooms.get(roomIdKey);//根据房号获取房间
            Iterator<Map.Entry<String, WebSocketChatRoom>> entries = chatRoomMap.entrySet().iterator();    //发送完后删除消息，此处遍历必须使用迭代器，不然会报错
            while (entries.hasNext()) {
                ConcurrentHashMap.Entry<String, WebSocketChatRoom> tempEntry = entries.next();
                String key = tempEntry.getKey();
                Session sessionValue = tempEntry.getValue().session;
                if(sessionValue==this.session){
                    entries.remove();
                    System.out.println("用户连接断开");
                }
            }
        }

        for(String roomIdKey:rooms.keySet()){
            System.out.println("房号：" + roomIdKey);
            ConcurrentHashMap<String,WebSocketChatRoom> tempChatMap = rooms.get(roomIdKey);
            for(String userIdKey:tempChatMap.keySet()){
                System.out.println("用户标识："+userIdKey+" websocket："+tempChatMap.get(userIdKey)+" 客户端session："+tempChatMap.get(userIdKey).session.toString());
            }
        }
    }


    /**
     * 用户连接异常
     * @param
     */
    @OnError
    public void onError(Throwable error){
//        System.out.println("发生错误");
        error.printStackTrace();
    }


//    private static MessageService messageService;
//
//    @Autowired
//    private void setMessageService(MessageService messageService){//使用这种方式才不会出现空指针错误
//        WebSocketChatRoom.messageService=messageService;
//    }
//
//    private static int onlineConectCount = 0;//连接数
//
//    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建时间格式对象
//
//    private static ConcurrentHashMap<String,WebSocketChatRoom> chatRoomMap = new ConcurrentHashMap<>();//存放socket对象,已登录对象客户端存放处
//
//    private static ConcurrentHashMap<String,WebSocketChatRoom> unLoginchatRoomMap = new ConcurrentHashMap<>();//存放socket对象，未登录客户端存放处
//
//    private Session session;//连接会话
//
//    private HashMap<String,String> messHashMap = new HashMap<>();//消息
//
//    public synchronized void joinRoom(String userId,Session session) throws IOException {
//        this.session = session;
//        chatRoomMap.put(userId,this);
//
////        System.out.println("=============加入后当前房间人数："+chatRoomMap.size());
////        for(String key:chatRoomMap.keySet()){
////            System.out.println("用户标识："+key+" websocket："+chatRoomMap.get(key)+" session："+chatRoomMap.get(key).session.toString());
////        }
////        System.out.println();
//    }
//
////    发送消息到聊天室所有用户
//    public void sendMessToAllUser(String mess) throws IOException {
////        System.out.println("messageToAllUser:"+mess);
//        for(String userId:chatRoomMap.keySet()){
//            chatRoomMap.get(userId).session.getBasicRemote().sendText(mess);
//        }
//
//    }
//
////    发送消息到聊天室指定用户
//    public void sendMessToTargetUser(String targetUserId,HashMap messMap) throws IOException {
//        String mess = JSONObject.fromObject(messHashMap).toString();
////        System.out.println("messageToTargetUser:"+mess);
//        boolean existStatus = false;
//        for(String userId:chatRoomMap.keySet()){
//            if(targetUserId.equals(userId)){
//                existStatus = true;
////                System.out.println(chatRoomMap.get(userId));
//                chatRoomMap.get(userId).session.getBasicRemote().sendText(mess);
//            }
//        }
//        if(!existStatus){
//            PfoMessage message = new PfoMessage();
//            message.setUserId(Long.parseLong(messHashMap.get("userId").toString()));
//            message.setTargetUserId(Long.parseLong(targetUserId));
//            message.setMessageText(messMap.get("chatContent").toString());
//            messageService.addMessage(message);
////            System.out.println("主键回填"+message.getMessageId());
//        }
//
//    }
//
//    @OnOpen
//    public void onOpen(@PathParam(value = "userId")String userId, Session session) throws IOException {
////        System.out.println(userId+"打开链接");
//        if(!userId.equals("null") && !userId.equals("")){
//            joinRoom(userId,session);
//        }else{
//            this.session = session;
//            onlineConectCount++;
////            messHashMap.put("userId","未登录用户"+String.valueOf(onlineConectCount));
//            unLoginchatRoomMap.put(String.valueOf(onlineConectCount),this);//直接存入
//        }
//    }
//
//    @OnMessage
//    public void onMessage(String content) throws IOException {
//        JSONObject JSONcontent = JSONObject.fromObject(content);
//        String state = JSONcontent.get("state").toString();
//
//        String userId = JSONcontent.get("userId").toString();
//
//        if(state.equals("inRoom")){
//            String userName = JSONcontent.get("userName").toString();
//            String userPhoto = JSONcontent.get("userPhoto").toString();
//            String chatContent = JSONcontent.get("chatContent").toString().replace("\n","<br>");
//            Object targetUserIdObj = JSONcontent.get("targetUserId");
//            String nowDate = df.format(new Date());
//
//            messHashMap.put("userId",userId);
//            messHashMap.put("userName",userName);
//            messHashMap.put("userPhoto",userPhoto);
//            messHashMap.put("chatContent",chatContent);
//            messHashMap.put("state",state);
//            messHashMap.put("date",nowDate);
//            //指明了消息发送的目标用户
//            if(null != targetUserIdObj){
//                String targetUserId=targetUserIdObj.toString();
//                messHashMap.put("targetUserId",targetUserId);
//                //并且其不为空
//                if(!targetUserId.equals("")){
//                    sendMessToTargetUser(targetUserId,messHashMap);
//                }
//            }else{
//                sendMessToAllUser(JSONObject.fromObject(messHashMap).toString());
//            }
//        }
//    }
//
//    /**
//     * 用户链接断开
//     */
//    @OnClose
//    public void onClose(){
////        System.out.println(this.session.toString());
//        chatRoomMap.remove(this);
//        System.out.println("用户连接断开");
//
//        Iterator<Map.Entry<String, WebSocketChatRoom>> entries = chatRoomMap.entrySet().iterator();    //发送完后删除消息，此处遍历必须使用迭代器，不然会报错
//        while (entries.hasNext()) {
//            ConcurrentHashMap.Entry<String, WebSocketChatRoom> tempEntry = entries.next();
//            String key = tempEntry.getKey();
//            Session sessionValue = tempEntry.getValue().session;
//            if(sessionValue==this.session){
//                entries.remove();
//            }
//        }
////        System.out.println("=============断开后当前房间人数："+chatRoomMap.size());
////        for(String key:chatRoomMap.keySet()){
////            System.out.println("用户标识："+key+" websocket："+chatRoomMap.get(key)+" session："+chatRoomMap.get(key).session.toString());
////        }
//    }
//
//
//    /**
//     * 用户连接异常
//     * @param
//     */
//    @OnError
//    public void onError(Throwable error){
////        System.out.println("发生错误");
//        error.printStackTrace();
//    }
}