$(document).ready(function () {
    $.ajax({
        url:"/pfo/user/getUser",
        type:"json",
        success:function (resultData) {
            if(resultData.code===0){
                var userId = $.cookie("userId");
                var userName = $.cookie("userName");
                var userType = $.cookie("userType");
                var userSex = $.cookie("userSex");
                var userBirth = $.cookie("userType");
                var userEmail = $.cookie("userEmail");
                var userSignature = $.cookie("userSignature");
                var userPhone = $.cookie("userPhone");
                var userPhoto = $.cookie("userPhoto");
                var oCoin = $.cookie("oCoin");
                $("#userImg img").attr("src",userPhoto);
                $("#userOption").html('<span class="logoutAction" style="color: white;cursor:pointer">退出</span>');
                $("#userImg").removeAttr("hidden");
                $("#userMessage").removeAttr("hidden");
                getUserMessageNum(userId);


            }else{
                $("#userOption").html('<a href="/pfo/html/user/login.html">登录/</a><a href="/pfo/html/user/regist.html">注册</a>');
                $("#StatusInfo").text("用户未登录");
                $("#userImg").attr("hidden","hidden");
                $("#userMessage").attr("hidden","hidden");
                $("#Status").show();

                setTimeout(function () {
                    $("#Status").hide()
                },1000)

            }
        },
        error:function (xhr) {
            console.warn(xhr)
        }
    })
})
//获取用户的消息
function getUserMessageNum(userId) {
    $.ajax({
        url:"/pfo/message/queryMessage",
        type:"get",
        data:{"targetUserId":userId},
        success:function (resultData) {
            if(resultData.code===0){
                var messageList=resultData.data;
                var messageNum=messageList.length;
                if(messageNum!==0){
                    $("#userMessageNum").text(messageNum);
                }
            }
        }
    })
}

function showUserMessage() {
    var userId=$.cookie("userId");
    if(null!=userId&&userId!=""&&userId!=undefined){}
    window.open("/pfo/html/user/chat.jsp?userId="+userId, "_blank");
}

$(document).on('click','.logoutAction',function () {
    $.ajax({
        url:"/pfo/user/userLogout",
        success:function (resultData) {
            if(resultData.code===0){
                window.location.reload();
                $("#StatusInfo").text("退出成功");
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide()
                },1000)
            }
        }
    })
})

$("#onlineCodeMyModal").on("shown.bs.modal",function () {
    $(".modal-backdrop").remove();

})


$("#maxSize").click(function () {
    $("#onlineCodeDialog").attr("class", "modal-dialog modal-lg")
})

$("#minSize").click(function () {
    $("#onlineCodeDialog").attr("class", "modal-dialog modal-md")
})

function javaComplier() {
    $(".modal-backdrop").remove();
    if($("#originCode").val() === ""){
        $("#StatusInfo").text("请输入源程序");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        },1000)
    }else{
        $.ajax({
            url:"http://localhost:8080/pfo/getJavaCompiler",
            data:{"originCode":$("#originCode").val()},
            success:function (resultData) {
                if(resultData.code == 0){
                    var runInfo = resultData.data;
                    var compileResult = $("#compileResult");
                    compileResult.text("");
                    compileResult.append("编译耗时："+runInfo.compilerTakeTime + " 编译信息："+runInfo.compilerMessage + " 编译状态："+runInfo.compilerSuccess+"\n");
                    compileResult.append("\n运行结果：\n"+runInfo.runResult);
                    compileResult.append("\n运行耗时："+runInfo.runTakeTime + " 运行状态："+runInfo.runSuccess);

                }else{
                    $("#compileResult").val(resultData.message);
                }
            }
        })
    }

}