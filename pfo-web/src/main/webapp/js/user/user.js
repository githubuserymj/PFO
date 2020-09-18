$(document).ready(function (){
    var href = window.location.href;//获取链接
    var JSONhrefParam=new Object();
    if (href.indexOf("?") != -1) {//有参数代表查看的
        var hrefParam = href.split("?")[1];
        var param = hrefParam.split("&");
        for(var i=0;i<param.length;i++){
            paramKey=param[i].split("=")[0];
            paramValue=param[i].split("=")[1];
            JSONhrefParam[paramKey]=paramValue;
        }
    }
    var paramUserId=JSONhrefParam["userId"];
    initUser(paramUserId);
});


//初始化用户中心
function initUser(paramUserId) {
    var userId=$.cookie("userId");
    var userName = $.cookie("userName");
    var userType = $.cookie("userType");
    var userPhoto = $.cookie("userPhoto");
    var userSignature = $.cookie("userSignature");
    var userSex = $.cookie("userSex");
    var userBirth = $.cookie("userBirth").split("&")[0];
    var userEmail = $.cookie("userEmail");
    if(null!==paramUserId&&paramUserId!==""&&paramUserId!==undefined&&userId!==paramUserId){
        userId=paramUserId;
        $("#editUserInfo").text("私聊");//移除用户编辑信息框
        $("#editUserInfo").attr("onclick","chat()");//添加聊天事件
        $("#editUserInfo").attr("id","startChat");//更改id
        $(".userSignUp").parent().remove();//查看别的用户中心时移除简历菜单
        $(".userSetting").parent().remove();//查看别的用户中心时移除设置菜单
        $(".userResume").parent().remove();//查看别的用户中心时移除简历菜单
        $("#startChat").text("私聊");
        var focusUserid=userId;
        var focusStatus=queryFocus(focusUserid);
        $(".addFocus").attr("focusStatus",focusStatus)
        if(focusStatus===true){
            $(".addFocus").text("已关注")
        }else{
            $(".addFocus").text("关注")
        }

        $.ajax({
            url:"/pfo/user/queryUsers?userId="+userId,
            async:false,
            success:function (resultData) {
                if(resultData.code===0){
                    var userData=resultData.data[0];
                    userName = userData.userName;
                    userType = userData.userType;
                    userPhoto = userData.userPhoto;
                    userSignature = userData.userSignature;
                    userSex = userData.gender;
                    userBirth = userData.birthday.split("T")[0];
                    userEmail = userData.userEmail;
                }
            }
        });

    }else{
        $(".addFocus").remove();
        $("#startChat").text("编辑");//移除用户编辑信息框
        $("#startChat").attr("onclick","");//添加聊天事件
        $("#startChat").attr("id","editUserInfo");//更改id
    }

    if(null !== userName && userName !== ""){
        $(".inforHead-userName").text(userName);
        $(".inforHead-userName").attr("userId",userId);
    }

    if(null !== userType && userType !== ""){
        $(".inforHead-userType").attr("userType",userType);
        if(userType==1){
            $(".inforHead-userType").text("普通用户");
            $(".inforHead-companyName").parent().remove();
        }
        if(userType==3){
            $(".inforHead-userType").text("公司用户");
            getCompany($(".inforHead-userName").attr("userid"));
        }
    }
    if(null !== userPhoto && userPhoto !== ""){
        $("#userPhoto").attr("src",userPhoto);
    }
    if(null !== userSex && userSex !== ""){
        $(".inforHead-userSex").text(userSex);
    }
    if(null !== userBirth && userBirth!== ""){
        $(".inforHead-userBirth").text(userBirth);
    }
    if(null !== userEmail && userEmail !== ""){
        $(".inforHead-userEmail").text(userEmail);
    }
    if(null !== userSignature && userSignature !== ""){
        $(".inforHead-userSignature").text(userSignature);
    }

    $(".oCoin").text($.cookie("oCoin"));
    getUserFans(userId);
    getUserCollection(userId);

};
//获取公司信息
function getCompany(userId) {
    $.ajax({
        url:"/pfo/company/queryCompany",
        type:"get",
        async:false,
        data:{"userId":userId},
        success:function (resultData) {
            if(resultData.code===0){
                var company=resultData.data[0];
                var companyId=company.companyId;
                var companyName=company.companyName;
                $(".inforHead-companyName").text(companyName);
                $(".inforHead-companyName").attr("companyId",companyId);
                $(".inforHead-companyName").attr("href","/pfo/html/company/company_website.html?companyId="+companyId);
                $(".inforHead-companyName").attr("target","_bank");
            }
        }
    })
}

$(".profile-menu li a").bind(
    'mouseenter',function () {
        $(this).css("background","#ececec")
        var menu = $(".profile-menu li a");
        for(var i=0;i<menu.length;i++) {
            if ($(menu[i]).attr("class").indexOf("active") > -1) {
                $(menu[i]).css("background","#25bb9b")
            }
        }
    }

)

$(".profile-menu li a").bind(
    'mouseleave',function () {
        var menu = $(".profile-menu li a");
        for(var i=0;i<menu.length;i++){
            if ($(menu[i]).attr("class").indexOf("active")> -1){
                $(menu[i]).css("background","#25bb9b")
            }
            else{
                $(menu[i]).css("background","white")
                $(menu[i]).css("color","black")
            }
        }
    }
);

$(".profile-menu li a").click(function () {
    var menu = $(".profile-menu li a");
    for(var i=0;i<menu.length;i++){
        $(menu[i]).css("background","white")
        $(menu[i]).css("color","black")
    }
    $(this).css("background","#25bb9b");
    $(this).css("color","white");
});


//鼠标移到用户头像处触发的事件
$("#userPhoto").bind(
    'mouseenter',function (){
        $(".uploadLogo").removeAttr("hidden");
    }
)
//鼠标离开到'上传logo'触发的事件
$(".uploadLogo").bind(
    'mouseleave',function (){
        $(".uploadLogo").attr("hidden","hidden");
    }
)
//点击'上传logo'触发的事件
$(".uploadLogo").click(function () {
    $("#uploadUserPhoto").click();
})
//用户选择图片时发生的事件
$("#uploadUserPhoto").change(function () {
    $("#uploadUserPhotoModal").modal("show");
    $(".userPrePhoto").attr("src",URL.createObjectURL($(this)[0].files[0]));
    $('.userPrePhoto').Jcrop({
        boxWidth:450,
        onChange: updatePreview,
        onSelect: updatePreview,
    });

})
//裁剪过程中，每改变裁剪大小执行该函数
function updatePreview(c) {
    var canvasWidth = $("#cut").css("height");
    var canvasHeight = $("#cut").css("height");
    var image = new Image();
    image.src=$(".userPrePhoto").attr("src");
    var cut = document.getElementById("cut");//获取2d画笔
    var ctx = cut.getContext("2d");
    if(parseInt(c.w) > 0) {
        ctx.drawImage(image, c.x, c.y, c.w, c.h, 0, 0, parseInt(canvasWidth), parseInt(canvasHeight));
    }
}

function changeCanvasWidth() {
    var canvasWidth = $(".canvasWidth").val();
    if(canvasWidth<=300 && canvasWidth>0){
        $("#cut").width(canvasWidth);
    }
}

function changeCanvasHeight() {
    var canvasHeight = $(".canvasHeight").val();
    if(canvasHeight<=300 && canvasHeight>0){
        $("#cut").height($(".canvasHeight").val());
    }
}

//将base64转换为文件
function dataURLtoFile(dataurl, filename) {
    var arr = dataurl.split(',');
    var mime = arr[0].match(/:(.*?);/)[1];
    var bstr = atob(arr[1]);
    var n = bstr.length;
    var u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}

//上传用户头像
$("#updateUserPhoto").click(function () {
        var formData = new FormData();
        var cut = document.getElementById("cut");
        var imgUrl = cut.toDataURL("image/png");//canvas转化为base64编码
        var imgFile = dataURLtoFile(imgUrl,"userPhoto.png");
        formData.append("userId",$.cookie("userId"));
        formData.append("multipartFile",imgFile);
        $.ajax({
            url:"/pfo/user/updateUser",
            type : 'post',
            data : formData,
            cache: false,   //上传文件无需缓存
            processData: false,   // 用于对参数进行序列化处理，这里必须设为false
            contentType:false,
            success:function (resultData) {
                if(resultData.code === 0){
                    $("#userPhoto").attr("src",imgUrl);
                    $("#userImg img").attr("src",imgUrl);
                    $("#uploadUserPhotoModal").modal('hide');

                }else {
                    $("#StatusInfo").text("头像上传失败");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    }, 1000)
                }
            }
        })

})

//点击聊天按钮
function chat() {
    window.location.href="/pfo/html/user/chat.jsp?userId="+$.cookie("userId")+"&chatUserId="+$('.inforHead-userName').attr("userid");
}

//点击编辑用户信息按钮
$("#editUserInfo").click(function () {
    $(".userSetting").click();
})

//点击左侧个人首页菜单
$(".userHome").click(function () {
    getUserFans($(".inforHead-userName").attr("userId"));
    getUserCollection($(".inforHead-userName").attr("userId"));
})

var finishQuestionList;

//点击左侧做题菜单
$(".userfinishQuestions").click(function () {
    $("#finishQuestions tbody").html("");
    $("#questionMenu li").click(function () {
        $("#questionMenu li").removeAttr("style");
        $("#questionMenu li").children().removeAttr("style");
        $(this).css({"background":"#25bb9b"});
        $(this).children().css({"color":"white"});
    });
    //请求所有做题记录
    $.ajax({
        url:"/pfo/question/queryAllExercisedByCondition",
        type:"get",
        data:{"userId":$(".inforHead-userName").attr("userId")},
        dataType:"json",
        success:function (pageInfoResult) {
            finishQuestionList = pageInfoResult.dataList;//所有题目
            var finishQuestionNum = finishQuestionList.length;
            $(".finishQuestionNum").text(finishQuestionNum);
            if(finishQuestionNum===0||finishQuestionNum===null){
                var html = '<div style="margin-top: 150px;text-align: center;">'
                    +'<img src="https://static.nowcoder.com//images/empty1.png" alt="">'
                    +'<div>你暂时没有做过题</div>'
                    +'</div>'
                    +'<div style="height: auto">'
                    +'<div style="width:100%;margin-top: 50px "><button class="btn" style="width: 100%;background: #25bb9b;color: white">去做题</button></div>'
                    +'</div>';
                $(".finishQuestionsContent").html(html);
            }
            else{
                for(var i=0;i<finishQuestionNum;i++){
                    var exercisedId=finishQuestionList[i].exercisedId;
                    var state = finishQuestionList[i].state;
                    var finishTime = finishQuestionList[i].finsihTime.split("T")[0];
                    var userAnswer = finishQuestionList[i].userAnswer;
                    var question = finishQuestionList[i].question;
                    var questionContent = question.questionContent;
                    var JSONquestionContent = JSON.parse(questionContent);
                    var questionName = JSONquestionContent.title;

                    if(questionName.length>30){
                        questionName = questionName.substring(0,30)+"...";
                    }
                    if(userAnswer.length>30){
                        userAnswer = userAnswer.substring(0,30)+"...";
                    }
                    var html = '<tr>'
                        +'<td>'+(i+1)+'</td>'
                        +'<td>'+questionName+'</td>'
                        +'<td>'+userAnswer+'</td>'
                        +'<td>'+finishTime+'</td>'
                        +'<td><img exercisedId='+exercisedId+' class="deleteQuestionInfo" src="/pfo/img/logo/delete.svg" style="width: 20px"></td>'
                        +'</tr>';
                    $("#finishQuestions tbody").append(html);
                    $("#finishQuestions tbody tr").attr("height","60px");
                    $("#finishQuestions tbody tr td").attr("vertical-align","middle");
                }
            }

        }
    });

});

//点击错题集时
$(".userWrongQuests").click(function () {
    $("#wrongQuestions tbody").html("");
    var num = 0;
    for(var i=0;i<finishQuestionList.length;i++){
        var state = finishQuestionList[i].state;
        if(state===3){
            num++;
            var exercisedId=finishQuestionList[i].exercisedId;
            var finishTime = finishQuestionList[i].finsihTime.split("T")[0];
            var userAnswer = finishQuestionList[i].userAnswer;
            var question = finishQuestionList[i].question;
            var questionContent = question.questionContent;
            var JSONquestionContent = JSON.parse(questionContent);
            var questionName = JSONquestionContent.title;

            if(questionName.length>30){
                questionName = questionName.substring(0,30)+"...";
            }
            if(userAnswer.length>30){
                userAnswer = userAnswer.substring(0,30)+"...";
            }
            var html = '<tr>'
                +'<td>'+num+'</td>'
                +'<td>'+questionName+'</td>'
                +'<td>'+userAnswer+'</td>'
                +'<td>'+finishTime+'</td>'
                +'<td><img exercisedId='+exercisedId+' class="deleteQuestionInfo" src="/pfo/img/logo/delete.svg" style="width: 20px"></td>'
                +'</tr>';
            $("#wrongQuestions tbody").append(html);
            $("#wrongQuestions tbody tr").attr("height","60px");
            $("#wrongQuestions tbody tr td").attr("vertical-align","middle");
        }
    }
    $(".wrongQuestionNum").text(num);
    if(num===0||num===null){
        var html = '<div style="margin-top: 150px;text-align: center;">'
            +'<img src="https://static.nowcoder.com//imagerongs/empty1.png" alt="">'
            +'<div>你暂时没有错题</div>'
            +'</div>'
            +'<div style="height: auto">'
            +'<div style="width:100%;margin-top: 50px "><button class="btn" style="width: 100%;background: #25bb9b;color: white">去做题</button></div>'
            +'</div>';
        $(".wQuestionsContent").html(html);
    }
})

//删除用户做题记录
$("body").on("click",".deleteQuestionInfo",function () {
    var nowUserId=$(".inforHead-userName").attr("userid");
    if(nowUserId!=$.cookie("userId")){
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        },1000)
    }else{
        var status=false;
        $.ajax({
            url:"/pfo/question/deleteExercised",
            type:"get",
            async:false,
            data:{"exercisedId":$(this).attr("exercisedid")},
            success:function (resultData) {
                if(resultData.code===0){
                    status=true;
                }else{
                    $("#StatusInfo").text(resultData.message);
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }
            }
        })
        if(status===true){
            var tr = $(this).parent().parent();
            tr.remove();
        }
    }

})


// 点击试卷菜单
$(".userFinishPapers").click(function () {
    $('#userFinishPapers tbody').html("");
    $.ajax({
        url:"/pfo/exam/queryAllExams",
        type:"post",
        data:{"userId":$(".inforHead-userName").attr("userId")},
        success:function (pageInfoResult) {
            if(pageInfoResult.totalSize != 0){
                var paperList = pageInfoResult.dataList;
                for(var i=0;i<paperList.length;i++){
                    var examId = paperList[i].examId;
                    var score = paperList[i].score;//试卷得分
                    var requestTime = paperList[i].finishTime;//试卷提交时间
                    var date = new Date(requestTime);
                    var year = date.getFullYear(); // 获取年份
                    var month = date.getMonth() + 1; //获取月份
                    var day = date.getDate(); //获取日期
                    var hour = date.getHours(); // 获取小时
                    var minute = date.getMinutes() ;// 获取分钟
                    var second = date.getSeconds() ;// 获取秒
                    requestTime = year+'/'+month+'/'+day+' '+hour+':'+minute+':'+second;
                    var paper = paperList[i].pfoPaper;//试卷
                    var paperName = paper.paperName;
                    var html = '<tr examId='+examId+'> <td onclick="showUserPaperInfo('+examId+')">'+(i+1)+'</td> <td onclick="showUserPaperInfo('+examId+')">'+paperName+'</td> <td onclick="showUserPaperInfo('+examId+')">'+score+'</td> <td onclick="showUserPaperInfo('+examId+')">'+requestTime+'</td> <td> <img src="/pfo/img/logo/delete.svg" alt="" id="deleteUserPaper" examId='+examId+' style="width: 20px"> </td> </tr>';
                    $('#userFinishPapers tbody').append(html);
                }
            }else{

            }
        }
    })
})
//点击用户帖子菜单
$(".userPost").click(function () {
    $("#post tbody").html('');
    $.ajax({
        url: "/pfo/post/queryAllPostsByUserId",
        type: "get",
        data: {"userId": $(".inforHead-userName").attr("userId")},
        success: function (resultData) {
            if(resultData.code===0){
                var postList = resultData.data;
                for(var i = 0;i<postList.length;i++){
                    var postId = postList[i].postId;
                    var postTitle = postList[i].postTitle.replace(/%2B/g,"+").replace(/%26/g,"&").replace(/%25/g,"%");
                    var postBody = postList[i].postBody.replace(/%2B/g,"+").replace(/%26/g,"&").replace(/%25/g,"%");
                    if(postTitle.length>20){
                        postTitle=postTitle.substring(0,20)+"...";
                    }
                    if(postBody.length>69){
                        postBody=postBody.substring(0,69)+"...";
                    }
                    var html = '<tr style="height: 100px"><td onclick="showUserPostInfo('+postId+')" style="vertical-align: middle;">'+(i+1)+'</td> <td onclick="showUserPostInfo('+postId+')" style="vertical-align: middle;">'+postTitle+'</td> <td onclick="showUserPostInfo('+postId+')" style="vertical-align: middle;">'+postBody+'</td> <td style="vertical-align: middle;"> <img onclick="editUserPost('+postId+')" src="/pfo/img/logo/edit.svg" alt="" style="width: 20px"> <img class="deleteUserPost" postId='+postId+' src="/pfo/img/logo/delete.svg" alt="" style="width: 20px"> </td> </tr>';
                    $("#post tbody").append(html);
                }
            }
        }
    })
})

//编辑用户发表的帖子
function editUserPost(postId) {
    window.location.href="/pfo/html/discussion/pfo_publish.html?postId="+postId
}
//点击用户讨论
$(".userDiscuss").click(function () {
    $("#discussTable tbody").html("");

    $.ajax({
        url:"/pfo/comment/queryAllCommentsByUserId?userId="+$(".inforHead-userName").attr("userid"),
        success:function (resultData) {
            if(resultData.code==0){
                var commentList=resultData.data;
                var j=0;
                for(var i=0;i<commentList.length;i++){
                    var commentId=commentList[i].commentId;
                    var postId=commentList[i].postId;
                    var commentContent=commentList[i].commentContent;
                    var deliverTime=commentList[i].deliverTime;
                    console.log("postId:"+postId)
                    if(null !=postId && postId!=""&&postId!=undefined){
                        j=j+1;
                        var html='<tr postId="'+postId+'">'
                            +'<td style="vertical-align: center">'+j+'</td>'
                            +'<td onclick="showUserPostInfo('+postId+')">'+commentContent+'</td>'
                            +'<td onclick="showUserPostInfo('+postId+')">'+deliverTime+'</td>'
                            +'<td><img onclick="deleteUserComment('+commentId+')" class="deleteUserComment" commentId='+commentId+' src="/pfo/img/logo/delete.svg" alt="" style="width: 20px"></td>'
                            +'</tr>';
                        $("#discussTable tbody").append(html);
                    }

                }
            }
        }
    })
})
//删除用户评论
function deleteUserComment(commentId) {
    var nowUserId=$(".inforHead-userName").attr("userid");
    if(nowUserId!=$.cookie("userId")) {
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        }, 1000)
    }else{
        $.ajax({
            url:"/pfo/comment/deleteById?commentId="+commentId,
            success:function (resultData) {
                if(resultData.code===0){
                    var deleteUserCommentList = $("#discussTable .deleteUserComment");
                    for(var i=0;i<deleteUserCommentList.length;i++){
                        if($(deleteUserCommentList[i]).attr("commentId")==commentId){
                            $(deleteUserCommentList[i]).parent().parent().remove();
                        }
                    }
                }
            }
        })
    }
}

// 点击用户收藏菜单
$(".userFavorite").click(function () {
    $('#favorites tbody').html("");
    getUserCollection($(".inforHead-userName").attr("userId"));
})

$(".focusFans").click(function () {
    $("#focusFansMenu li").removeAttr("style");
    $("#focusFansMenu li").children().removeAttr("style");
    $(this).css({"background":"#25bb9b"});
    $(this).children().css({"color":"white"});
});
//点击顶部粉丝菜单
$(".fansMenu").click(function () {
    $("#fansTable tbody").html("")
    getUserFansIdList();
})

//點擊用戶關注/粉絲菜單
$(".userFocusUsers").click(function () {
    $("#userFocusUsers tbody").html("");
    getUserFocusUserIdList();
})
//获取所关注人数的id
function getUserFocusUserIdList() {
    $.ajax({
        url:"/pfo/focus/queryFocus",
        type:"post",
        data:{"userId":$(".inforHead-userName").attr("userId")},
        success:function (resultData) {
            if(resultData.code==0){
                var focusUserIdList = resultData.data;
                var focusUserNum = focusUserIdList.length;//用戶所關注的人的數目
                $(".focusNum").text(focusUserNum)
                for(var i=0;i<focusUserIdList.length;i++){
                    var focusUserId = focusUserIdList[i].focusUserId;
                    getFocusUser(focusUserId);
                }

            }
        }
    })
}
//获取用户所有粉丝的id
function getUserFansIdList() {
    $.ajax({
        url:"/pfo/focus/queryFocus",
        type:"post",
        data:{"focusUserId":$(".inforHead-userName").attr("userId")},
        success:function (resultData) {
            if(resultData.code==0){
                var userIdList = resultData.data;
                var userNum = userIdList.length;//用戶所關注的人的數目
                $(".fansNum").text(userNum)
                for(var i=0;i<userIdList.length;i++){
                    var userId = userIdList[i].userId;
                    getFansUser(userId);
                }
            }
        }
    })
}

//点击左侧报名菜单
$(".userSignUp").click(function () {
    $(".sign-up-list").html("");
    getSignUp();
})

//格式化字符日期
function parseTimeStr(timeStr) {
    var date = new Date(timeStr);
    var year = date.getFullYear(); // 获取年份
    var month = date.getMonth() + 1; //获取月份
    var day = date.getDate(); //获取日期
    var hour = date.getHours(); // 获取小时
    var minute = date.getMinutes() ;// 获取分钟
    var second = date.getSeconds() ;// 获取秒
    var parseTime = year+'/'+month+'/'+day+' '+hour+':'+minute+':'+second;
    return parseTime;
}
function getSignUp() {
    $.ajax({
        url:"/pfo/signUp/queryByUserIdWithPage?userId="+$.cookie("userId"),
        header:{"Access-Control-Allow-Origi":"Access-Control-Allow-Origi"},
        success:function (pageResultDate) {
            if(pageResultDate.totalSize>0){
                var dataList=pageResultDate.dataList;
                for(var i=0;i<dataList.length;i++){
                    var signUpId=dataList[i].signUpId;//报名数据id
                    var signUpTime=parseTimeStr(dataList[i].deliverTime);//报名时间
                    var recruitment=dataList[i].recruitment;
                    var recruitmentId=dataList[i].recruitmentId;
                    var recruitmentTitle=recruitment.recruitmentTitle;//招聘信息标题
                    var companyId=recruitment.companyId;//招聘信息标题
                    var position=recruitment.position;//职位
                    var recruitmentImg=recruitment.recruitmentImg;//招聘信息缩略图
                    console.log(recruitmentTitle+position)

                    var company=recruitment.pfoCompany;
                    var companyName=company.companyName;
                    var html='<div style="height:100px;border-bottom: 1px #e1e1e1 solid;display: flex;align-items: center;;margin: 5px 0">'
                        +'<div style="width: 20%;text-align: center">'
                        +'<a target="_blank" href="/pfo/html/recruit/recruit_detail.html?recruitmentId='+recruitmentId+'"><img src="'+recruitmentImg+'" alt="" style="width: 100px;height: 70px"></a>'
                        +'</div>'
                        +'<div style="width: 20%;text-align: center"><a target="_blank" href="/pfo/html/recruit/recruit_detail.html?recruitmentId='+recruitmentId+'">'+recruitmentTitle+'</a></div>'
                        +'<div style="width: 20%;text-align: center">'+position+'</div>'
                        +'<div style="width: 20%;text-align: center">'+signUpTime+'</div>'
                        +'<div style="width: 20%;text-align: center"><a target="_blank" href="/pfo/html/company/company_website.html?companyId='+companyId+'">'+companyName+'</div></div>'
                        +'</div>';

                    $(".sign-up-list").append(html);

                }
            }
        }
    })
}

// 点击左侧用户简历
$(".userResume").click(function () {
    $(".rusumeList").html("");

    $.ajax({
        url:"/pfo/resume/queryResumes?userId="+$(".inforHead-userName").attr("userId"),
        success:function (resultData) {
            if(resultData.code==0){
                var userResumeList=resultData.data;
                for(var i=0;i<userResumeList.length;i++){
                    var resumeId=userResumeList[i].resumeId;
                    var userId=userResumeList[i].userId;
                    var resumeContext=userResumeList[i].resumeContext;
                    var resumeTitle=resumeContext.split("|")[0];
                    var resumeImgUrl=resumeContext.split("|")[1];
                    var html='<div style="position: relative"> <div></div> <div class="resumeTitle">'+resumeTitle+'</div> <div style="position: absolute;right: 0"> <a href="'+resumeImgUrl+'" target="_Blank"><img src="/pfo/img/logo/watch.svg" alt=""></a> <span resumeTitle="'+resumeTitle+'" resumeId="'+resumeId+'" class="updateUserResume" data-toggle="modal" data-target="#userResumeModal"><img src="/pfo/img/logo/edit.svg" alt=""></span> <span><img class="deleteUserResume" onclick="deleteUserResume('+resumeId+')" resumeId="'+resumeId+'" src="/pfo/img/logo/delete.svg" alt=""></span> </div> </div>'
                    $(".rusumeList").append(html);
                }
            }
        }
    })
})


$(".addUserResume").click(function () {
    $("#userResumeModal .modal-footer button").text("上传");
})

$("body").on('click',".updateUserResume",function () {
    $(".userResumeTitle").val($(this).attr("resumeTitle"));
    $("#userResumeModal .modal-footer button").text("更新");
    $("#userResumeModal .modal-footer button").attr("onclick","updateUserResume("+$(this).attr("resumeId")+")")
})
//更新用户简历
function updateUserResume(resumeId) {
    var nowUserId=$(".inforHead-userName").attr("userid");
    if(nowUserId!=$.cookie("userId")) {
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        }, 1000)
    }else{
        var formData = new FormData();
        var userResumeTitle=$(".userResumeTitle").val();
        var userResumeFileImg = document.getElementById("userResumeFile").files[0];

        formData.append("userId",$.cookie("userId"));
        formData.append("resumeId",resumeId);
        formData.append("resumeContext",userResumeTitle);
        formData.append("multipartFile",userResumeFileImg);
        $.ajax({
            url:"/pfo/resume/updateResume",
            type : 'post',
            data : formData,
            cache: false,   //上传文件无需缓存
            processData: false,   // 用于对参数进行序列化处理，这里必须设为false
            contentType:false,
            success:function (resultData) {
                if(resultData.code === 0){
                    $("#userResumeModal .modal-footer button").attr("onclick","uploadUserResume()");

                    $(".userResume").click();
                    $("#StatusInfo").text("更新成功");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    }, 1000)

                }else {
                    $("#StatusInfo").text("更新失败");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    }, 1000)
                }
            }
        })
    }
}

$("#userResumeFile").change(function () {
    $(".uploadUserResumeStatus").text("图片载入完成!");
})

//删除用户简历
function deleteUserResume(resumeId) {
    var nowUserId=$(".inforHead-userName").attr("userid");
    if(nowUserId!=$.cookie("userId")) {
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        }, 1000)
    }else{
        $.ajax({
            url:"/pfo/resume/deleteResume?resumeId="+resumeId,
            success:function (resultData) {
                if(resultData.code==0){
                    var resumeList=$(".deleteUserResume");
                    for(var i=0;i<resumeList.length;i++){
                        if($(resumeList[i]).attr("resumeId")==resumeId){
                            $(resumeList[i]).parent().parent().parent().remove();
                        }
                    }
                }
            }
        })
    }

}

//点击上传简历图片
$(".uploadUserResumeLogo").click(function () {
    $(".uploadUserResumeStatus").text("正在选入图片!");
    $("#userResumeFile").click();
})

//上传用户简历
function uploadUserResume() {
    var formData = new FormData();
    var userResumeFileImg = document.getElementById("userResumeFile").files[0];

    formData.append("userId",$.cookie("userId"));
    formData.append("userResumeTitle",$(".userResumeTitle").val());
    formData.append("multipartFile",userResumeFileImg);
    $.ajax({
        url:"/pfo/resume/addResume",
        type : 'post',
        data : formData,
        cache: false,   //上传文件无需缓存
        processData: false,   // 用于对参数进行序列化处理，这里必须设为false
        contentType:false,
        success:function (resultData) {
            if(resultData.code === 0){
                $(".userResume").click();
            }else {
                $("#StatusInfo").text("上传失败");
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide()
                }, 1000)
            }
        }
    })
}

//点击左侧用户设置菜单
$(".userSetting").click(function () {
    $('.changeUserBasicInfo .userName').val($.cookie('userName'));
    $('.changeUserBasicInfo .userSex').val($.cookie('userSex'));
    var userBirth = $.cookie('userBirth').split("&")[0];
    $('.changeUserBasicInfo .userBirth').val(userBirth);
    $('.changeUserBasicInfo .userEmail').val($.cookie('userEmail'));
    $('.changeUserBasicInfo .userSignature').val($.cookie('userSignature'));
    $('.changeUserPhone .userPhone').text($.cookie("userPhone"));

    $('.changeUserPwd .userPhone').text($.cookie("userPhone"));
    requestCodeImg();
})

//更新用户基础信息
function updateUserBasicInfo(){
    var userName = $(".userName").val();
    var userSex = $(".userSex").val();
    var userBirth = $(".userBirth").val();
    var userEmail = $(".userEmail").val();
    var userSignature = $(".userSignature").val();
    $.ajax({
        url:"/pfo/user/updateUser",
        type: 'get',
        data:{'userId':$.cookie("userId"),'userName':userName,'gender':userSex,'userBirthStr':userBirth,'userEmail':userEmail,'userSignature':userSignature},
        success:function (resultData) {
            if(resultData.code==0){
                $(".inforHead-userName").text(userName);
                $(".inforHead-userSex").text(userSex);
                $(".inforHead-userBirth").text(userBirth);
                $(".inforHead-userEmail").text(userEmail);
                $(".inforHead-userSignature").text(userSignature);

                $("#StatusInfo").text("保存成功");
                $("#Status").show();
                setTimeout(function () {
                    $("#Status").hide()
                },1000)
            }
        }
    });
}

// 获取请求图形验证码
function requestCodeImg() {
    $.ajax({
        url:"/pfo/user/getImgCode",
        success:function (resultData) {
            if(resultData.code == 0){
                $(".imgCode").attr("src",resultData.data);
            }else{
                $(".imgCode").attr("alt",resultData.message);
            }
        }
    })
}


// 获取短信
function getSmsCode() {
    var userNewPwd = $(".changeUserPwd .newPassword").val();
    var imgCode = $(".changeUserPwd .imgCodeValue").val();
    var userPhone = $.cookie("userPhone");
    if(null===userNewPwd || userNewPwd==="" || null===imgCode ||imgCode===""){
        $("#logInfo").css("width","230px");
        $("#StatusInfo").text("请输入完整参数");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        },1000)
    }else{
        $.ajax({
            url:"/pfo/user/getSmsCode",
            data:{"imgCode":imgCode,"phone":userPhone},
            success:function (resultData) {
                if(resultData.code == 0){
                    timer();
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
        $(".changeUserPwd .sendSmsCode").text("获取验证码");
        $(".changeUserPwd .sendSmsCode").removeClass("disabled");
        wait = 60;
    } else {
        $(".changeUserPwd .sendSmsCode").addClass("disable");
        $(".changeUserPwd .sendSmsCode").text(wait + "秒后重发");
        wait--;
        setTimeout(function () {
            timer()
        }, 1000);
    }
}



//点击更新密码按钮
$(".changeUserPwd .btn_changeUserPwd").click(function () {
    var userPhone = $(".changeUserPwd .userPhone").text();
    var newUserPwd = $(".changeUserPwd .newPassword").val();
    var smsCode = $("#smsCodeValue").val();

    if(null !==userPhone&&userPhone!==""&&null!==newUserPwd&&newUserPwd!==""&&null!==smsCode&&smsCode!==""){
        $.ajax({
            url:"/pfo/user/updateUserPwd",
            data:{'userPhone':userPhone,'newUserPwd':newUserPwd,"smsCode":smsCode},
            type:"json",
            success:function (resultData) {
                if(resultData.code===0){
                    $("#StatusInfo").text("更新成功");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }else{
                    $("#StatusInfo").text("更新失败");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }
            }
        })
    }else{
        $("#StatusInfo").text("请输入合法参数");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        },1000)
    }
});

// 獲取用戶所關注的人
function getFocusUser(focusUserId) {
    $.ajax({
        url:"/pfo/user/queryUsers",
        type:"get",
        data:{"userId":focusUserId},
        success:function (resultData) {
            if(resultData.code==0){
                var user = resultData.data[0];//獲取用戶關注的用戶
                var focusUserId = user.userId;
                var userName = user.userName;
                var userSignature = user.userSignature;
                var userPhoto = user.userPhoto;

                var html = '<tr focusUserId ='+focusUserId+'> <td onclick="showUserCenter('+focusUserId+')"><img src='+userPhoto+' alt="" style="border-radius: 50%;width: 60px;height: 60px"></td> <td onclick="showUserCenter('+focusUserId+')" style="vertical-align: middle;">'+userName+'</td> <td onclick="showUserCenter('+focusUserId+')" style="vertical-align: middle;">做题量：4322 正确率：70%</td> <td style="vertical-align: middle;">'+userSignature+'</td> <td style="vertical-align: middle;"> <img onclick="cancelFocus('+focusUserId+')" src="/pfo/img/logo/cancelFocus.svg" alt="" style="width: 25px"> </td> </tr>';
                $("#focusTable tbody").append(html);
            }
        }
    })
}

// 獲取用戶所關注的人
function getFansUser(userId) {
    $.ajax({
        url:"/pfo/user/queryUsers",
        type:"get",
        data:{"userId":userId},
        success:function (resultData) {
            if(resultData.code==0){
                var user = resultData.data[0];//獲取用戶關注的用戶
                var focusUserId = user.userId;
                var userName = user.userName;
                var userSignature = user.userSignature;
                var userPhoto = user.userPhoto;
                var html = '<tr focusUserId ='+focusUserId+'> <td onclick="showUserCenter('+focusUserId+')"><img src='+userPhoto+' alt="" style="border-radius: 50%;width: 60px;height: 60px"></td> <td onclick="showUserCenter('+focusUserId+')" style="vertical-align: middle;">'+userName+'</td> <td onclick="showUserCenter('+focusUserId+')" style="vertical-align: middle;">做题量：4322 正确率：70%</td> <td style="vertical-align: middle;"> '+userSignature+' </td> </tr>';
                $("#fansTable tbody").append(html);
            }
        }
    })
}

//展示用户中心
function showUserCenter(userId) {
    window.open("/pfo/html/user/user.html?userId="+userId,'target');
};


//获取用户的粉丝数
function getUserFans(userId) {
    $.ajax({
        url:"/pfo/focus/queryFocus",
        type:"get",
        data:{"focusUserId":userId},
        success:function (resultData) {
            if(resultData.code == 0){
                var userFans = resultData.data;
                var userFansNum = userFans.length;
                $(".userFansNum").text(userFansNum);
            }else{
                $(".userFansNum").text("0");
            }
        }
    })
}

// 获取用户的收藏的帖子/试题id后轉入getPostInfo執行
function getUserCollection(userId) {
    id=0;
    $.ajax({
        url:"/pfo/favorite/queryFavorite",
        type:"post",
        // async:"false",//设置为false待任务全部完成
        data:{"userId":userId},
        success:function (resultData) {
            if(resultData.code == 0){
                var userFavoriteList = resultData.data;
                var userFavoritesNum = userFavoriteList.length;//获取收藏的帖子数
                $(".userFavoritesNum").text(userFavoritesNum);
                for(var i =0;i<userFavoritesNum;i++){
                    var favoriteId = userFavoriteList[i].favoriteId;
                    var postId = userFavoriteList[i].postId;//获取收藏的帖子id
                    getPostInfo(favoriteId,postId,i+1);//查询id对应的帖子信息
                }
            }else{
                $(".userFavoritesNum").text("0");
            }
        }
    })
}
var id=0;
//獲取用戶收藏的帖子信息
function getPostInfo(favoriteId,postId) {
    $.ajax({
        url:"/pfo/post/queryPostByPostId?postId="+postId,
        success:function (resultData) {
            if(resultData.code==0){
                id=id+1;
                var postList = resultData.data;
                var post = postList[0];
                var postTitle = post.postTitle;
                var postBody = post.postBody;
                if(postTitle.length>20){
                    postTitle=postTitle.substring(0,20)+"...";
                }
                if(postBody.length>69){
                    postBody=postBody.substring(0,69)+"...";
                }
                var html = '<tr favoriteId='+favoriteId+' style="height: 100px" postId='+postId+'> <td onclick="showUserPostInfo('+postId+')" style="vertical-align: middle;">'+id+'</td> <td onclick="showUserPostInfo('+postId+')" style="vertical-align: middle;">'+postTitle+'</td> <td onclick="showUserPostInfo('+postId+')" style="vertical-align: middle;">'+postBody+'</td> <td style="vertical-align: middle;"> <img onclick="cancelCollect('+favoriteId+')" src="/pfo/img/logo/cancelCollect.svg" alt="" style="width: 20px"> </td> </tr>';
                $('#favorites tbody').append(html);
                }
            }
    })
}

$("body").on('mouseenter','.favoriteAction',function () {
    // $(".favoriteAction").attr("size",2)
    var elem = $('.favoriteAction');

        elem[0].fireEvent("onmousedown");

})


//跳轉到試卷詳情信息
function showUserPaperInfo(paperId) {
    window.open("/pfo/html/exam/paperAnalytic.html?paperId="+paperId,"target");
}

//跳轉到帖子詳情信息
function showUserPostInfo(postId) {
    window.open("/pfo/html/discussion/pfo_postInfo.html?postId="+postId,"target");
}

//用户删除做卷记录
$("body").on('click',"#deleteUserPaper",function () {
    var nowUserId=$(".inforHead-userName").attr("userid");
    if(nowUserId!=$.cookie("userId")) {
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        }, 1000)
    }else{
        var status = 1;
        $.ajax({
            url:"/pfo/exam/deleteExam",
            async:false,
            data:{'examId':$(this).attr('examId')},
            success:function (resultData) {
                if(resultData.code==0){
                    status=0;
                }else{
                    $("#StatusInfo").text("操作失败");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }
            }
        })
        if(status===0){
            var tr = $(this).parent().parent();
            tr.remove();
        }
    }

})

// 用户删除自己发表的帖子
$("body").on('click','#userPost .deleteUserPost',function() {
    var nowUserId=$(".inforHead-userName").attr("userid");
    if(nowUserId!=$.cookie("userId")) {
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        }, 1000)
    }else{
        var status = 1;//操作狀態，0：成功；1：失敗
        $.ajax({
            url:"/pfo/post/deletePostTagsByPostId?postId="+$(this).attr('postId'),
            async: false,
            success:function (resultData) {
                if(resultData.code == 0){
                    status = 0;
                }else{
                    $("#StatusInfo").text("操作失败");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }
            }
        })
        if(status == 0){
            var tr = $(this).parent().parent();
            tr.remove();
        }
    }

})

//每打开他人中心需查询关注情况
function queryFocus(focusUserId) {
    var focusStatus=false;
    var userId=$.cookie("userId");
    $.ajax({
        url:"/pfo/focus/queryFocus",
        async:false,
        data:{"userId":userId,"focusUserId":focusUserId},
        success:function (resultData) {
            if(resultData.code===0){
                focusStatus=true
            }
        }
    });
    return focusStatus;
}

//加关注
function addFocus() {
    var userId=$.cookie("userId");
    var focusUserId=$(".inforHead-userName").attr("userid");
    if($(".addFocus").attr("focusStatus")==="true"){
        cancelFocus(focusUserId)
    }else{
        $.ajax({
            url:"/pfo/focus/addFocus",
            data:{"userId":userId,"focusUserId":focusUserId},
            success:function (resultData) {
                if(resultData.code==0){
                    $(".addFocus").text("已关注")
                    $(".addFocus").attr("focusStatus",true);
                }
            }
        })
    }
};



//用户选择取消關注
function cancelFocus(focusUserId) {
    var userId=$.cookie('userId');
    var nowUserId=$(".inforHead-userName").attr("userid");

    if(nowUserId==userId||(nowUserId!=userId&&focusUserId==nowUserId)){
        $.ajax({
            url:"/pfo/focus/deleteFocus",
            async: false,
            data:{'userId': userId,'focusUserId':focusUserId},
            success:function (resultData) {
                if(resultData.code===0){
                    $(".addFocus").text("关注");
                    $(".addFocus").attr("focusstatus",false);
                    var trList=$("#focusTable tbody tr");
                    for(var i=0;i<trList.length;i++){
                        if($(trList[i]).attr("focususerid")==focusUserId){
                            $(trList[i]).remove()
                        }
                    }

                }else{
                    $("#StatusInfo").text("操作失败");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }
            }
        });
    }else{
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        }, 1000)
    }

}

//用户选择取消收藏
function cancelCollect(favoriteId) {
    var nowUserId=$(".inforHead-userName").attr("userid");
    if(nowUserId!=$.cookie("userId")) {
        $("#StatusInfo").text("此操作不属于当前用户");
        $("#Status").show();
        setTimeout(function () {
            $("#Status").hide()
        }, 1000)
    }else{
        $.ajax({
            url:"/pfo/favorite/deleteFavorite",
            async: false,
            data:{'favoriteId':favoriteId},
            success:function (resultData) {
                if(resultData.code == 0){
                    var trList=$("#favorites tbody tr");
                    for(var i=0;i<trList.length;i++){
                        if($(trList[i]).attr("favoriteid")==favoriteId){
                            $(trList[i]).remove()
                        }
                    }
                }else{
                    $("#StatusInfo").text("操作失败");
                    $("#Status").show();
                    setTimeout(function () {
                        $("#Status").hide()
                    },1000)
                }
            }
        })
    }

}



