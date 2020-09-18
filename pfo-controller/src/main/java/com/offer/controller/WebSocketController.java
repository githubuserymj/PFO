package com.offer.controller;

import com.offer.util.SocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @Describe websocket 消息推送(个人和群发)
 * @created ipromisemr
 * @time: 2019/9/18 15:52
 **/
@Controller
public class WebSocketController {

    @Autowired
    private SocketServer socketServer;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("admin")
    public String admin(Model model) {
        // 获取在线人数
        int num = socketServer.getOnlineNum();
        // 获取所有在线用户（client）
        List<String> onlineUsers = socketServer.getOnlineUsers();

        model.addAttribute("num", num);
        model.addAttribute("users", onlineUsers);
        return "admin";
    }

    @RequestMapping("sendMsg")
    @ResponseBody
    public String sendMsg(String msg, String username) {
        String[] persons = username.split(",");
        SocketServer.sendMany(msg, persons);
        return "success";
    }

    /**
     * 群发至所有用户
     * @param msg
     * @return
     */
    @RequestMapping("sendAll")
    @ResponseBody
    public String sendAll(String msg) {
        // 调用SocketServer类推送至所有用户的方法
        SocketServer.sendAll(msg);
        return "success";
    }
}
