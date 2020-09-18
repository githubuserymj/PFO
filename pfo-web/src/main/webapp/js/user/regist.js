//注册方式
var registWay='common';
$(".registWay").click(function () {
    $(this).css("background","#007bff");
    $(this).attr("status","active")
    if($(this).attr("way") == "common"){
        location.reload();
    }
    if($(this).attr("way") == "company"){
        $("span[way='common']").css("background","#ced4da");
        registWay='company';
        companyRegistWay();
    }
});
var username;
var phone;
var verifyCode;
var pwd;
var againPwd;
var companyName;
var companyAddress;
var qualificationFile;
//检查输入框的合法性
function check() {
    username = document.getElementById("username").value;
    phone = document.getElementById("user-phone").value;
    verifyCode = document.getElementById("verify-code").value;
    pwd = document.getElementById("password").value;
    againPwd = document.getElementById("again-password").value;

    if(username==""){
        document.getElementById("input-username-info").innerHTML="账号不能为空！"
    }
    if(phone==""){
        document.getElementById("input-phone-info").innerHTML="手机号不能为空！"
    }
    if(verifyCode==""){
        document.getElementById("input-verifyCode-info").innerHTML="验证码不能为空！"
    }
    if(pwd==""){
        document.getElementById("input-pwd-info").innerHTML="密码不能为空！"
    }
    if(againPwd==""){
        document.getElementById("input-again-pwd-info").innerHTML="二次密码不能为空！"
    }
    if(pwd!=againPwd){
        document.getElementById("input-again-pwd-info").innerHTML="前后密码不一致"
    }
    if(null != username && username != "" && null != phone && phone != "" && null != verifyCode && verifyCode != "" && null != pwd && pwd != "" && null != againPwd && againPwd != "" && pwd == againPwd){
        if(registWay === "common") {
            commonRegist();
        }

    }
}

function companyFormcheck() {
    check();
    companyRegist();
}
//普通用户注册
function commonRegist() {
    $.ajax({
        url: "/pfo/user/addUser",
        data: {
            "userName": username,
            "userPassword": againPwd,
            "userPhone": phone,
            "registSmsCode": $("#verify-code").val()
        },
        method: "post",
        success: function (resultData) {
            if (resultData.code == 0) {
                $("#StatusInfo").text("注册成功");
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide()
                }, 1000)
                window.location.href = "/pfo/html/user/login.html";
            } else {
                $("#StatusInfo").text(resultData.message);
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide();
                }, 1000)
            }
        },
        error: function (xhr) {
            console.warn(xhr)
        }
    })
}

//公司用户注册
function companyRegist() {
    companyName = $("#companyName").val();
    companyAddress = $("#companyAddress").val();
    qualificationFile = $("#loadFile")[0].files[0];
    var companyRegistForm = new FormData();
    //注册人数据
    companyRegistForm.append("userName",username);
    companyRegistForm.append("userPassword",againPwd);
    companyRegistForm.append("userPhone",phone);
    companyRegistForm.append("registSmsCode",$("#verify-code").val());
    //公司数据
    companyRegistForm.append("companyName",companyName);
    companyRegistForm.append("companyAddress",companyAddress);
    if(qualificationFile === null){
        alert("文件为空")
    }
    companyRegistForm.append("multipartFile",qualificationFile);

    $.ajax({
        url: "/pfo/company/addCompany",
        data: companyRegistForm,
        type : 'post',
        cache: false,   //上传文件无需缓存
        processData: false,   // 用于对参数进行序列化处理，这里必须设为false
        contentType:false,
        success: function (resultData) {
            if (resultData.code == 0) {
                $("#StatusInfo").text("注册成功");
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide()
                }, 1000)
                window.location.href = "/pfo/html/user/login.html";
            } else {
                $("#StatusInfo").text(resultData.message);
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide();
                }, 1000)
            }
        },
        error: function (xhr) {
            console.warn(xhr)
        }
    })
}


function checkUserNameInput() {
    var username = document.getElementById("username").value;
    if(username!=""){
        document.getElementById("input-username-info").innerHTML=""
    }
}

function checkPhoneInput() {
    var userphone = document.getElementById("user-phone").value;
    if(userphone!=""){
        document.getElementById("input-phone-info").innerHTML=""
    }
}

function checkVerifyCodeInput() {
    var verifyCode = document.getElementById("verify-code").value;
    if(verifyCode!=""){
        document.getElementById("input-verifyCode-info").innerHTML=""
    }
}


function checkPwdInput() {
    var pwd = document.getElementById("password").value;
    if(username!=""){
        document.getElementById("input-pwd-info").innerHTML=""
    }
}
function checkAgainPwdInput() {
    var againpwd= document.getElementById("again-password").value;
    if(againpwd!=""){
        document.getElementById("input-again-pwd-info").innerHTML=""
    }
}
//页面加载完就会触发的事件
window.onload=function(){
    requestCodeImg();
};

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
            data:{"imgCode":imgCode,"phone":$("#user-phone").val()},
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

function commonRegistWay() {
    $("#btn-company-next").attr("id","btn-regist");
    $("#btn-regist").text("注册")
    $("#btn-regist").attr("onclick","check()")
};

function companyRegistWay() {
    $("#btn-regist").attr("id","btn-company-next");
    $("#btn-company-next").text("下一步>>")
    $("#btn-company-next").attr("onclick","companyForm()");
};
function companyForm() {
    $("#registBody").children().hide();
    var companyForm;
    var companyName='<div class="form-group"> <div> <label for="companyName" class="control-label">公司名：</label> <span id="input-companyName-info" style="float: right"></span> </div> <div> <input type="text" oninput="checkPhoneInput(event)" onporpertychange="checkPhoneInput(event)" class="form-control" id="companyName"placeholder="请输入公司名称"> </div> </div>';
    var companyAddress='<div class="form-group"> <div> <label for="companyAddress" class="control-label">公司地址：</label> <span id="input-companyAddress-info" style="float: right"></span> </div> <div> <div class="input-group mb-3"> <div class="input-group-prepend" id="location" data-toggle="modal" data-target="#mapModal"> <span class="input-group-text" style="height: 32px"><img src="/pfo/img/tools/location.png" alt="" style="height: 24px"></span> </div><input type="text" oninput="checkPhoneInput(event)" onporpertychange="checkPhoneInput(event)" class="form-control" id="companyAddress"placeholder="标注公司地址" readonly style="background: white"> </div></div> </div>';
    var qualification = '<div class="form-group"> <div> <label for="qualification" class="control-label">公司资质：</label> <span id="input-qualification-info" style="float: right"></span> </div> <div id="qualificationImgArea" style="width: 100%;height: 260px;background: #999;border-radius: 3%"> <img id="qualification" src="http://img1.bsw360.cn/2018/3/3/20180303111626908.jpg" alt="公司资质图片" style="width: 100%;height: 260px"> <input id="loadFile" type="file" hidden="hidden"></div> </div>';
    var companyRegistButton = '<div class="form-group"> <div class=""> <button class="btn btn-primary" id="btn-companyRegist" type="submit" onclick=companyFormcheck()>注册</button> </div> </div>';
    companyForm = companyName + companyAddress + qualification +companyRegistButton;
    $("#registBody").append(companyForm);

}

$("body").on("click","#qualification",function(){
    $("#loadFile").click()
});

$("body").on("change","#loadFile",function () {
    $("#qualification").attr('src',URL.createObjectURL($(this)[0].files[0]));
})

$("body").on("click","#location",function () {
    // window.location.href = "/pfo/html/common/baiduMap.html"
    // alert("地图")
})

//模态框地图--------------------------
// 百度地图API功能
var map = new BMap.Map("mapBody",{minZoom: 4, maxZoom: 19});
//初始化地图，指明中心点和地图级别
map.centerAndZoom(new BMap.Point(120.644014,31.284756), 15);

// 开启鼠标滚轮缩放功能，仅对PC上有效
map.enableScrollWheelZoom(true);

// 添加带有定位的导航控件
var navigationControl = new BMap.NavigationControl({
    // 靠左上角位置
    anchor: BMAP_ANCHOR_TOP_LEFT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_LARGE,
    // 启用显示定位
    enableGeolocation: true
});
map.addControl(navigationControl);
//根据定位初始化地图展示到当前地址
var geolocation = new BMap.Geolocation();
geolocation.getCurrentPosition(function(r){
    if(this.getStatus() == BMAP_STATUS_SUCCESS){
        var mk = new BMap.Marker(r.point);
        map.addOverlay(mk);
        map.panTo(r.point);
    }
    else {
        console.log('failed'+this.getStatus());
    }
},{enableHighAccuracy: true})


//右上角地区选择
var size = new BMap.Size(10, 20);
map.addControl(new BMap.CityListControl({
    anchor: BMAP_ANCHOR_TOP_RIGHT,
    offset: size,
    // 切换城市之间事件
    // onChangeBefore: function(){
    //    alert('before');
    // },
    // 切换城市之后事件
    // onChangeAfter:function(){
    //   alert('after');
    // }
}));



// 获取关键字搜索地址
$("#searchAddress").click(function theLocation() {
    var local = new BMap.LocalSearch(map, {
        renderOptions:{map: map}
    });
    local.search($("#address").val());

})


//增加跳动点
function addDancePoint(lng,lat) {
    var point = new BMap.Point(lng, lat);
//    可通过下面两个创建自定义标注
//    var myIcon = new BMap.Icon("/jsdemo/img/fox.gif", new BMap.Size(300,157));
//    var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
    var marker2 = new BMap.Marker(point);  // 创建标注
    map.addOverlay(marker2);
    marker2.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    marker2.enableDragging();//可拖动
}

//给地图添加点击事件
map.addEventListener("click", function(e) {
    var geocoder = new BMap.Geocoder();
    geocoder.getLocation(e.point, function(rs) {
        var lng = e.point.lng;
        var lat = e.point.lat;
        addDancePoint(lng,lat);
        console.log("lng:"+lng);
        console.log("lat:"+lat);
        var province = rs.addressComponents.province;
        var city = rs.addressComponents.city;
        var district = rs.addressComponents.district;
        var street = rs.addressComponents.street;
        var streetNumber = rs.addressComponents.streetNumber;
        var address = province+city+district+street+streetNumber;
        console.log(rs.addressComponents); //结构化的地址描述(object)
        $("#province").val(province);
        $("#city").val(city);
        $("#district").val(district);
        $("#address").val(address);
        $("#lng").val(lng);
        $("#lat").val(lat);

        console.log(street); //街道
        console.log(streetNumber); //门牌号
    });

});

//标注点
var data_info = [[120.644014,31.284756,"地址：北京市东城区王府井大街88号乐天银泰百货八层"],
    [116.406605,39.921585,"地址：北京市东城区东华门大街"],
    [116.412222,39.912345,"地址：北京市东城区正义路甲5号"]
];
//信息窗口设置
var opts = {
    width : 250,     // 信息窗口宽度
    height: 400,     // 信息窗口高度
    title : "信息窗口" , // 信息窗口标题
    enableMessage:true//设置允许信息窗发送短息
};


//循环创建标注点
for(var i=0;i<data_info.length;i++){
    var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
    var content = data_info[i][2];
    map.addOverlay(marker);               // 将标注添加到地图中
    addClickHandler(content,marker);
}
//添加点击事件，点击后打开某地址的信息提示框
function addClickHandler(content,marker){
    marker.addEventListener("click",function(e){
        openInfo(content,e)}
    );
}
//打开信息提示框
function openInfo(content,e){
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    var sContent =
        "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>天安门</h4>" +
        "<img style='float:right;margin:4px' id='imgDemo' src='https://www.jq22.com/img/logo.png' width='139' height='104' title='天安门'/>" +
        "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" +
        "</div>";
//        var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
}

//增加标注点
function addPoint() {
//        类似这种
    var point_info = [[116.417854,39.921988,"地址：北京市东城区王府井大街88号乐天银泰百货八层"],
        [116.406605,39.921585,"地址：北京市东城区东华门大街"],
        [116.412222,39.912345,"地址：北京市东城区正义路甲5号"]
    ];
    for(var i=0;i<point_info.length;i++){
        var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]));  // 创建标注
        var content = data_info[i][2];
        map.addOverlay(marker);               // 将标注添加到地图中
        addClickHandler(content,marker);
    }
}

//删除具体某个标注点
function deletePoint(){
    var allOverlay = map.getOverlays();
    for (var i = 0; i < allOverlay.length -1; i++){
        if(allOverlay[i].getLabel().content == "我是id=1"){
            map.removeOverlay(allOverlay[i]);
            return false;
        }
    }
}

//增加右键菜单
var menu = new BMap.ContextMenu();
var txtMenuItem = [
    {
        text:'确定',
        callback:function(){
            //确定信息，传入
            var address = $("#address").val();//详细地址
            var lng = $("#lng").val();
            var lat = $("#lat").val();
            var detailedAddress = address+"|"+lng+"&"+lat;
            $("#companyAddress").val(detailedAddress);
            $("#mapModal").modal('hide');
        }
    },

];
for(var i=0; i < txtMenuItem.length; i++){
    menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
}
map.addContextMenu(menu);


// 用经纬度设置地图中心点
function theLocation(lng,lat){
    map.clearOverlays();
    var new_point = new BMap.Point(lng,lat);
    var marker = new BMap.Marker(new_point);  // 创建标注
    map.addOverlay(marker);              // 将标注添加到地图中
    map.panTo(new_point);
}






