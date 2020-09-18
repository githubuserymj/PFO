var inputUserInfo = "账号不能为空！";
var inputPwdInfo = "密码不能为空！";
var loginWay = "userAndPwd";

$(document).keyup(function(event){
    if(event.keyCode ==13){
        check();
    }
});

function check() {
    var username_phone = document.getElementById("userName-phone").value;
    var pwd_smsCode = document.getElementById("password-smsCode").value;
    if(username_phone == ""){
        document.getElementById("input-user-info").innerHTML = inputUserInfo;
    }
    if(pwd_smsCode == ""){
        document.getElementById("input-pwd-info").innerHTML = inputPwdInfo;
    }
    if(username_phone != "" && pwd_smsCode != ""){
        $.ajax({
            url:"/pfo/user/userLogin",
            method:"post",
            data:{"name_phone":username_phone,"password_smsCode":pwd_smsCode,"loginWay":loginWay},
            type:"json",
            success:function (resultData) {
                if(resultData.code == 0){
                    document.getElementById("userImg").removeAttribute("hidden")
                    $("#StatusInfo").text("登录成功");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                    window.location.href = "/pfo/";
                }else{
                    $("#StatusInfo").text(resultData.message);
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
    }
}
function checkUserInput() {
    var user = document.getElementById("userName-phone").value;
    if(user!=""){
        document.getElementById("input-user-info").innerHTML=""
    }
}

function checkPwdInput() {
    var pwd = document.getElementById("password-smsCode").value;
    if(pwd!=""){
        document.getElementById("input-pwd-info").innerHTML=""
    }
}

function verifyCodeLogin() {
    var userLable = document.getElementById("user-lable");
    var user = document.getElementById("userName-phone");
    user.placeholder="请输入手机号";
    userLable.innerHTML="手机号：";
    inputUserInfo = "手机号不能为空！";
    inputPwdInfo = "验证码不能为空！";
    loginWay = "phoneAndSmsCode";
    var getVerifyCode = document.getElementById("getVerifyCode")
    if(getVerifyCode!=null){
        getVerifyCode.parentNode.removeChild(getVerifyCode);
    }
    var pwdLogin = document.getElementById("pwdLogin");

    if(pwdLogin!=null){
        pwdLogin.parentNode.removeChild(pwdLogin);
    }
    requestCodeImg();
    var appendImgCodeHtml = '<div class="input-group mb-3"> <div class="input-group-prepend"> <img src="" alt="" id="imgCode" onclick="requestCodeImg()" style=""> </div> <input type="text" oninput="checkUserInput(event)" onporpertychange="checkUserInput(event)" class="form-control" id="imgCodeInput" placeholder="请输入图形验证码"> </div>';
    $(".imgCode").html(appendImgCodeHtml);
    var appendBoxhtml = '<a id="pwdLogin" href="login.html" style="float: right">>>密码登录</a>';
    document.getElementById("checkbox").insertAdjacentHTML("beforeEnd",appendBoxhtml);
    var appendHtml = '<div id="getVerifyCode" class="input-group-append"><button id="btnGetCode" class="btn btn-primary" onclick="getSmsCode()">获取验证码</button></div>';
    document.getElementById("password-smsCode").insertAdjacentHTML("afterEnd",appendHtml);
    document.getElementById("pwd-lable").innerHTML="验证码：";
    document.getElementById("password-smsCode").placeholder="请输入验证码";
    return false;

}

// 获取请求图形验证码
function requestCodeImg() {
    $.ajax({
        url:"/pfo/user/getImgCode",
        success:function (resultData) {
            if(resultData.code == 0){
                $("#imgCode").attr("src",resultData.data);
            }else{
                $("#imgCode").attr("alt",resultData.message);
            }
        }
    })
}

// 获取短信
function getSmsCode() {
    var imgCode = $("#imgCodeInput").val();
    if(null == imgCode || imgCode == ""){
        $("#logInfo").css("width","230px");
        $("#StatusInfo").text("图形验证码不能为空");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        },1000)
    }else{
        $.ajax({
            url:"/pfo/user/getSmsCode",
            data:{"imgCode":imgCode,"phone":$("#userName-phone").val()},
            success:function (resultData) {
                if(resultData.code == 0){
                    $("#StatusInfo").text("短信已发送");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                    timer();

                }else{
                    $("#logInfo").css("width","250px");
                    $("#StatusInfo").text(resultData.message);
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }
            }
        })
    }
}

//60秒才能再次发送
var wait = 60;
//倒计时
function timer() {
    if (wait == 0) {
        $("#btnGetCode").text("获取验证码");
        $("#btnGetCode").removeAttr("disabled");
        wait = 60;
    } else {
        $("#btnGetCode").attr("disabled", "true");
        $("#btnGetCode").text(wait + "秒后重发");
        wait--;
        setTimeout(function () {
            timer()
        }, 1000);
    }
}

