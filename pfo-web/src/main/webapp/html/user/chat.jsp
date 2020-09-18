<%--
  Created by IntelliJ IDEA.
  User: YMJ
  Date: 2019-09-26
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PFO聊天室</title>
    <script src="/pfo/js/jquery-3.4.1.js"></script>
    <script src="/pfo/js/jquery.cookie.js"></script>
    <script src="/pfo/js/common/baseTopNavigation.js"></script>


</head>
<body style="background:#f1f1f1">
<div style="margin: 0;padding: 0;background: #f1f1f1">
    <div style="margin: 10px auto;width: 81.6%;box-shadow: 0px 0px 5px 0 black;border: solid 1px white">
        <div class="roomTitle" style="background:white;color:#6c757d;display: flex;justify-content: center;font-size: 20px;height: 40px">PFO online聊天室</div>
        <div id="chatContent" style="min-height: 400px;display:flex;flex-direction:column;background:white;border: 1px white solid;overflow-y:scroll;box-shadow: black 0 0 0.1em 0 inset">
            <div id="chatInfo" style="display: flex;justify-content:center;margin-top: 10px;color: coral;"></div><%--窗口提示信息--%>
            <%--聊天内容--%>
        </div>
        <div id="chatOperator">
            <div>
                <div class="input-group" style="margin-bottom: 0;padding: 20px 0">
                    <textarea id="messageContent" type="text" class="form-control" placeholder="发送内容"></textarea>
                    <div class="input-group-append">
                        <span id="sendMessage" class="input-group-text" style="cursor: pointer;background: #25bb9b;color: white">发送</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--底部导航--%>
<script src="/pfo/js/common/footer.js"></script>

</body>
<script src="/pfo/js/user/login.js"></script>
<script src="/pfo/js/bootstrap.js"></script>
<script src="/pfo/js/common/baseTopNavigationJS.js"></script>
<link rel="stylesheet" href="/pfo/css/bootstrap.css">
<link rel="stylesheet" href="/pfo/css/common/baseTopNavigation.css">
<link rel="stylesheet" href="/pfo/css/global.css">
<link rel="stylesheet" href="/pfo/css/common/footer.css">



<script>
    var windowW=$(window).width();
    var windowH=$(window).height();
    $("#chatContent").css({"height":(windowH*0.65)+"px"});
    if(!window.WebSocket){
        alert("您的浏览器不支持websocket")
    }

    var state;//状态标志，joinRoom:加入聊天室，exitRoom：退出聊天室,inRoom：在聊天室中
    var content = {
        "userId":$.cookie("userId"),
        "userName":$.cookie("userName"),
        "userPhoto":$.cookie("userPhoto"),
    };

    var href = window.location.href;//获取链接
    var JSONhrefParam=new Object();
    if (href.indexOf("?") != -1) {
        var hrefParam = href.split("?")[1];
        var param = hrefParam.split("&");
        for(var i=0;i<param.length;i++){
            paramKey=param[i].split("=")[0];
            paramValue=param[i].split("=")[1];
            JSONhrefParam[paramKey]=paramValue
        }
        content["userId"]=JSONhrefParam["userId"];
        content["targetUserId"]=JSONhrefParam["chatUserId"];
    }

    if(null == content["targetUserId"] || content["targetUserId"] == "" || content["targetUserId"] == $.cookie("userId") || content["targetUserId"]===undefined){
        $("#chatOperator").remove()
    }

    // 獲取用戶信息
    function getUserInfo(userId,messageId,deliverTime,messageType,messageText) {
        $.ajax({
            url:"/pfo/user/queryUsers",
            type:"get",
            data:{"userId":userId},
            success:function (resultData) {
                if(resultData.code==0){
                    var user = resultData.data[0];//獲取用戶關注的用戶
                    var originUserId = user.userId;
                    var originUserName = user.userName;
                    var originUserSignature = user.userSignature;
                    var originUserPhoto = user.userPhoto;
                    var html = '<div style="margin: 0 0 20px 10px">'
                        +'<ul style="list-style: none">'
                        +'<li style="float: left">'
                        +'<a target="_blank" href="/pfo/html/user/chat.jsp?userId='+$.cookie("userId")+'&chatUserId='+originUserId+'"><img onclick="" class="ml-auto rounded-circle" src="'+originUserPhoto+'" alt="" style="width: 70px;border: 2px white solid;"></a>'
                        +'</li>'

                        +'<li style="float: left;">'
                        +'<div style="padding: 0 20px;display: flex;justify-content: space-between;align-items: flex-end">'
                        +'<div style="">'
                        +'<strong style="font-size: 1.2em">'+originUserName+'</strong>'
                        +'</div>'
                        +'<div style=";font-size: 0.9em">'+deliverTime+'</div>'
                        +'</div>'
                        +'<div style="background:#6c757d;padding:10px;color:white;min-width: 300px;max-width:500px;border-radius: 5px">'
                        +messageText
                        +'</div>'
                        +'</li>'

                        +'</ul>'
                        +'</div>';
                    $("#chatContent").append(html);
                }
            }
        })

        $.ajax({
            url:"/pfo/message/deleteMessage?=3",
            type:"get",
            data:{"messageId":messageId},
            success:function (resultData) {
                if(resultData.code==0){
                    console.log("成功删除一条已读消息，id："+messageId)
                }
            }
        })
    }

    var webSocket=null;

    $(document).ready(function () {
        if(JSONhrefParam["chatUserId"]==null||JSONhrefParam["chatUserId"]==""||JSONhrefParam["chatUserId"]==undefined){
            // //获取用户的消息
            $.ajax({
                url:"/pfo/message/queryMessage",
                type:"get",
                data:{"targetUserId":$.cookie("userId")},
                success:function (resultData) {
                    if(resultData.code===0){
                        var messageList=resultData.data;
                        var messageNum=messageList.length;
                        $("#chatInfo").text("您有"+messageNum+"条未读消息！")
                        for(var i=0;i<messageNum;i++){
                            var messageId=messageList[i].messageId;
                            var userId=messageList[i].userId;
                            var deliverTime=messageList[i].deliverTime;
                            var targetUserId=messageList[i].targetUserId;
                            var messageType=messageList[i].messageType;
                            var messageText=messageList[i].messageText;
                            getUserInfo(userId,messageId,deliverTime,messageType,messageText);
                        }
                    }
                }
            })
        }else{
//            webSocket = new WebSocket("ws://101.132.147.42/pfoChat/room1/"+$.cookie("userId"));
            webSocket = new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/pfoChat/room1/"+$.cookie("userId"));
            if(null !== webSocket){
                $("#StatusInfo").text("连接已建立");
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide()
                },1000)
            }
        }


        //打开连接
        webSocket.open = function () {
            $("#StatusInfo").text("初始化完成");
            $("#Status").show();
            setTimeout(function () {
                $("#Status").hide()
            },1000)
        }

        //通信中
        webSocket.onmessage = function (event) {
            var messStr=event.data;
            var JSONmess = jQuery.parseJSON(messStr);
            var serverUserId = JSONmess.userId;
            var serverUserName = JSONmess.userName;
            var serverChatContent = JSONmess.chatContent;
            var serverDate = JSONmess.date;
            var serverUserPhoto = JSONmess.userPhoto;
            $(".roomTitle").text(serverUserName)
            var chatHtml = '<div style="margin: 0 0 20px 10px">'
            +'<ul style="list-style: none">'
                +'<li style="float: left">'
                +'<a target="_blank" href="/pfo/html/user/chat.jsp?userId='+$.cookie("userId")+'&chatUserId='+serverUserId+'"><img onclick="" class="ml-auto rounded-circle" src="'+serverUserPhoto+'" alt="" style="width: 70px;border: 2px white solid;"></a>'
                +'</li>'

                +'<li style="float: left;">'
                +'<div style="padding: 0 20px;display: flex;justify-content: space-between;align-items: flex-end">'
                +'<div style="">'
                +'<strong style="font-size: 1.2em">'+serverUserName+'</strong>'
                +'</div>'
                +'<div style=";font-size: 0.9em">'+serverDate+'</div>'
                +'</div>'
                +'<div style="background:#6c757d;padding:10px;color:white;min-width: 300px;max-width:500px;border-radius: 5px">'
                +serverChatContent
                +'</div>'
                +'</li>'

                +'</ul>'
                +'</div>';
            $("#chatContent").append(chatHtml)
        }

        webSocket.onclose = function () {
            $("#StatusInfo").text("连接断开");
            $("#Status").show();
            setTimeout(function () {
                $("#Status").hide()
            },1000)
        }
        webSocket.onerror = function (event) {
            $("#StatusInfo").text("连接发生错误");
            $("#Status").show();
            setTimeout(function () {
                $("#Status").hide()
            },1000)
        }
    })


    $("#sendMessage").click(function () {
        if(null !== webSocket){
            content["roomId"] = "room1";
            content["state"] = "inRoom";
            content["chatContent"]=$("#messageContent").val();
            webSocket.send(JSON.stringify(content))
            var myDate = new Date();
            var year=myDate.getFullYear();        //获取当前年
            var month=myDate.getMonth()+1;   //获取当前月
            var date=myDate.getDate();            //获取当前日
            var hour=myDate.getHours();              //获取当前小时数(0-23)
            var minute=myDate.getMinutes();          //获取当前分钟数(0-59)
            var second=myDate.getSeconds();

            var nowDateTime=year+'-'+month+"-"+date+" "+hour+':'+minute+":"+second;
            var chatHtml = '<div style="margin: 0 10px 20px auto">'
                +'<ul style="list-style: none">'
                +'<li style="float: left;">'
                +'<div style="padding: 0 20px;display: flex;justify-content: space-between;align-items: flex-end">'
                +'<div style=";font-size: 0.9em">'+nowDateTime+'</div>'
                +'<div style="">'
                +'<strong style="font-size: 1.2em">'+$.cookie("userName")
                +'</strong>'
                +'</div>'
                +'</div>'
                +'<div style="background:#6c757d;padding:10px;color:white;min-width: 300px;max-width:500px;border-radius: 5px">'
                +content["chatContent"]+'</div> </li>'
                +'<li style="float: left">'
                +'<img class="ml-auto rounded-circle" src="'+$.cookie("userPhoto")+'" alt="" style="width: 70px;border: 2px white solid;">'
                +'</li></ul> </div>';
            $("#chatContent").append(chatHtml)
            $("#messageContent").val("");
        }
    })

    //断开
    $("#stopConnectServer").click(function () {
        content["state"] = "exitRoom";
        webSocket.send(JSON.stringify(content));
    })

    function connectUser(targetUserId) {
        window.location.href="/pfo/html/user/chat.jsp?userId="+$.cookie("userId")+"&chatUserId="+targetUserId;
    }
</script>
</html>
