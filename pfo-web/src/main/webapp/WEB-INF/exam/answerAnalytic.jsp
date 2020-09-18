<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>答案解析</title>

    <script src="/pfo/js/jquery-3.4.1.js"></script>
    <script src="/pfo/js/bootstrap.js"></script>
    <script src="/pfo/js/wangEditor.js"></script>
    <script src="/pfo/js/jquery.cookie.js"></script>
    <script src="/pfo/js/common/baseTopNavigation.js"></script>
    <script src="/pfo/js/common/baseTopNavigationJS.js"></script>
    <link rel="stylesheet" href="/pfo/css/wangEditor.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/common/baseTopNavigation.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/global.css" type="text/css">

    <style>
        .result-box {
            margin-top: 50px;
            border-radius: 5px 5px 0 0;
        }

        .result-content {
            border: lightgrey 1px solid;
            border-top: none;
        }

        .result-item {
            background: #fff;
            padding: 20px 30px;
            border-bottom: 1px solid #ededed;
        }

        .result-num-list {
            letter-spacing: -4px;
            margin-right: -10px;
            list-style: none;
        }

        .result-num-list li {
            letter-spacing: 0;
            display: inline-block;
            *display: inline;
            *zoom: 1;
            margin-right: 5px;
            margin-bottom: 20px;
        }

        .result-num-list li a {
            background: #ff431e;
            display: block;
            color: #fff;
            font-size: 14px;
            width: 32px;
            height: 32px;
            line-height: 32px;
            text-align: center;
            position: relative;

        }

        .result-question-box {
            padding: 30px;
            border-bottom: 1px solid #ececec;
        }

        .result-question {
            font-size: 14px;
            line-height: 1.6;
            margin-bottom: 20px
        }

        .question-number {
            background: #1abc9c;
            border-radius: 12px;
            float: left;
            color: #fff;
            padding: 5px 7px;
            line-height: 12px;
            font-weight: 400;
        }

        .user-answer {
            background: #dfdddd;
            padding: 20px 30px;
            border-bottom: 1px solid #d4d2d2;
        }

        .design-answer-box {
            font-size: 14px;
            border-radius: 6px;
            padding: 15px;
            background: #e9eef3;
            border: 1px solid #dfdfdf;
            margin-bottom: 20px;
        }


        .w-e-text-container{
            height: 140px !important;
        }

        .oprt-tool {
            float: right;
            font-size: 14px;
            list-style: none;
        }

        .oprt-tool li {
            float: left;
            position: relative;
        }

        .oprt-item {
            color: #888;
            display: block;
            padding-left: 30px;
            line-height: 25px;
            background: url(/pfo/img/tools/问题.png) no-repeat 0px -3px;
        }

        .official-answer {
            background: #fff;
            padding: 20px 30px;
            border-bottom: 1px solid #ededed;
        }

        .knowledge-point {
            padding: 20px 30px;
            background: #dfdddd;
            margin-top: -1px;
            border-top: 1px solid #d9e2e7;
            border-bottom: 1px solid #d9e2e7;
        }

        .tag-label {
            border-radius: 12px;
            border: 1px solid #ddd;
            display: inline-block;
            padding: 2px 10px;
            color: #333;
            font-size: 12px;
            margin-right: 2px;
            background: #fff;
            margin-bottom: 5px;
            line-height: 16px;
            position: relative;
        }


        .analysis-comment {
            width: 100%;
            padding: 20px 30px;
            border-bottom: 1px solid #ededed;
            margin-bottom: 40px;
        }

        .answer-content {
            width: 100%;
            position: relative;
        }

        .post-back {
            margin-top: 20px;
            width: 100%;
        }

        .analytic-discuss-num {
            margin-top: 10px;
            display: inline-block;
        }

        #jsDealAnswer {
            display: inline-block;
            text-align: center;
            padding: 10px 0;
            width: 110px;
            color: #fff;
            font-size: 14px;
            border-radius: 3px;
            border: none 0;
            cursor: pointer;
            line-height: normal;
            outline: 0;
            white-space: nowrap;
        }

        .answer-list-item {
            border-bottom: 1px solid #eee;
            padding-bottom: 15px;
            position: relative;
            padding-top: 15px;
            margin-bottom: 50px;
        }

        .votebar {
            float: left;
            margin-right: 18px;
        }

        .votebar .up {
            background: #eff6fa;
            border: none;
            border-radius: 3px;
            color: #25bb9b;
            cursor: pointer;
            display: block;
            font-weight: 500;
            height: 24px;
            line-height: 24px;
            position: relative;
            width: 38px;
            outline: 0;
        }

        .answer-info {
            margin-bottom: 10px;
            height: 30px;
            line-height: 30px;
        }

        .answer-head {
            display: block;
            float: left;
            width: 30px;
            height: 30px;
            border: 1px solid #ededed;
            overflow: hidden;
            margin-right: 15px;
            -webkit-border-radius: 50%;
            -moz-border-radius: 50%;
            border-radius: 50%;
        }

        .answer-name {
            font-size: 14px;
            vertical-align: middle;
            display: inline-block;
            *zoom: 1;
            *display: inline;
        }

        .answer-brief {
            font-size: 14px;
            line-height: 1.8;

        }

        .answer-legend {
            margin-top: 10px;
            color: #999;
            font-size: 12px;
            line-height: 20px;
        }
    </style>

</head>
<body>
<div class="container">
    <div class="result-box bg-white">
        <!--条件展示-->
        <div>
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a id="paperAnalytic" class="nav-link" href="#">评估报告</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#">答案解析</a>
                </li>
            </ul>
        </div>
        <!--答案解析框-->
        <div class="result-content">
            <!--题目列表-->
            <div class="result-item">
                <h6>每题得分</h6>
                <div class="result-num-list"></div>
            </div>
            <!--题目框-->
            <div class="result-question-box">
                <div class="result-question">
                    <span class="question-number">1</span>
                    <p class="question-title"></p>
                    <p class="question-context"></p>
                </div>
            </div>
        </div>
        <!--用户答案-->
        <div class="user-answer">
            <h6>你的答案</h6>
            <div class="design-answer-box" id="editor">
                <p>未回答该问题</p>
            </div>
            <ul class="oprt-tool">
                <li><a href="" class="oprt-item">划入错题</a></li>
                <li><a href="" class="oprt-item">求助解答</a></li>
                <li><a href="" class="oprt-item">标记一下</a></li>
                <li><a href="" class="oprt-item">分享</a></li>
            </ul>
        </div>
        <!--参考答案-->
        <div class="official-answer">
            <h6>参考答案</h6>
            <div class="design-answer-box">
                <div id="show-answer"></div>
            </div>
        </div>
        <!--涉及知识点-->
        <div class="knowledge-point">
            <h6>本题知识点</h6>
            <div class="tags-box">

            </div>
        </div>
        <!--评论区-->
        <div class="analysis-comment">
            <div class="answer-content">
                <!--回帖展示模块-->
                <div class="post-back">


                </div>
                <!--回帖功能-->
                <div class="replyBack" hidden>
                    <div id="commentEditor"></div>
                    <div id="publish" class="container">
                        <button id="replyBac" class="btn container btn-primary">提交</button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

</div>

<!--底部栏-->
<script src="/pfo/js/common/footer.js" type="application/javascript"></script>
<link rel="stylesheet" href="/pfo/css/common/footer.css" type="text/css">
<script>

    window.onload = function () {

        /*初始化题目列表*/
        var pageList = ''
        for (var i = 1; i <= ${questionNum}; i++) {
            pageList += '<li class="error-order">' +
                '<a class="qPages" onclick="getQuestion(' + i + ')" href="#">' + i +
                '</a>' +
                '</li>'
        }
        $('.result-num-list').html(pageList)

        var questionId
        var type
        var content
        var answerAnalysis
        var officialanswer

        getQuestion(1)

    }

    /*异步获取题目*/
    function getQuestion(page) {
        $('.question-number').html(page)
        var params = {
            currPage: page,
            ids: '${exam.pfoPaper.questionListId}'
        }
        $.get({
            url: "/pfo/exam/nextQuestion",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: params,
            success: function (data) {
                questionId = data.dataList[0].questionId
                $('.result-question-box').attr("data-id", questionId)
                type = data.dataList[0].questionType
                content = JSON.parse(data.dataList[0].questionContent)
                officialanswer = data.dataList[0].answer
                answerAnalysis = data.dataList[0].answerAnalysis
                initModel(type, content, officialanswer, answerAnalysis)
                getExercised(questionId)
                getTsgs(questionId)
                getComments(questionId)
            }
        })
    }

    /*初始化编辑器*/
    var E = window.wangEditor
    var editor = new E('#editor')
    // 自定义菜单配置
    editor.customConfig.menus = [];
    editor.create()
    editor.$textElem.attr('contenteditable', false)

    var userAnswer

    /*获取做题记录*/
    function getExercised(questionId) {
        var userId = $.cookie("userId")

        $.get({
            url: "/pfo/question/queryAllExercisedByCondition",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: 'pageSize=1&userId=' + userId + '&questionId=' + questionId,
            success: function (data) {
                console.log(data)
                userAnswer = data.dataList[0].userAnswer
                editor.txt.html(userAnswer)
            }
        })
    }

    /*初始化问题模板*/
    function initModel(type, content, officialanswer, answerAnalysis) {
        $('.question-type').text(type)
        /*获取试题题目*/
        $('.question-title').text(content.title)
        var sheet = ''
        /*初始化单选题模板*/
        if ("单选题" == type) {

            for (var i = 0; i < content.options.length; i++) {
                sheet += '<div class="radio">'
                sheet += '<label><input type="radio" name="optradio" value="' + content.options[i].alias + '">'
                sheet += content.options[i].alias + '. ' + content.options[i].text
                sheet += '</label></div>'
            }
        }

        /*初始化不定向选择题模板*/
        if ("不定项选择题" == type) {
            for (var i = 0; i < content.options.length; i++) {
                sheet += '<div class="form-check">'
                sheet += '<label class="form-check-label">'
                sheet += '<input type="checkbox" class="form-check-input" name="cks" value="' + content.options[i].alias + '">'
                sheet += content.options[i].alias + '. ' + content.options[i].text
                sheet += '</label></div>'
            }
        }

        /*初始化判断题模板*/
        if ("判断题" == type) {
            sheet += '<div class="radio">'
            sheet += '<label><input type="radio" name="tfradio" value="对">对'
            sheet += '</label></div>'
            sheet += '<div class="radio">'
            sheet += '<label><input type="radio" name="tfradio" value="错">错'
            sheet += '</label></div>'
        }

        /*初始化填空题模板*/
        if ("填空题" == type) {
            for (var k = 1; k <= content.blank; k++) {
                sheet += '<div class="input-group mb-3">'
                sheet += '<div class="input-group-prepend">'
                sheet += '<span class="input-group-text">'
                sheet += k
                sheet += '</span></div><input id="blank' + k + '" type="text" name="blankInput" class="form-control" placeholder="填入第' + k + '空的答案"></div>'
            }
        }
        $('.question-context').html(sheet)
        $('#show-answer').html(officialanswer)
        $('.comment-text').html(answerAnalysis)

    }

    /*获取试题标签*/
    function getTsgs(questionId) {
        $.get({
            url: "/pfo/question/queryTagsByQuestionId",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: 'questionId=' + questionId,
            success: function (data) {
                console.log(data.data)
                $('.tags-box').html('')
                var tags = data.data
                for (var i = 0; i < tags.length; i++) {
                    $('.tags-box').append('<a class="tag-label">' + tags[i].tagName + '</a>')
                }

            }
        })
    }

    $('#paperAnalytic').click(function () {
        window.location.href = "/pfo/html/exam/paperAnalytic.html?examId=${examId}&correctNum=${correctNum}&questionNum=${questionNum}&consumingTime=${consumingTime}"

    })

    <!--富文本编辑器回复事件-->
    $("#replyBac").click(function () {
        $.get({
            async: false,
            url: "/pfo/comment/addComment",
            dataType: "json",
            data: "userId=" + $.cookie("userId") + "&questionId=" + $('.result-question-box').attr("data-id") + "&commentContent=" + replyEditor.txt.html(),
            success: function (data) {
                if (data.code == 0) {
                    alert("评论成功")
                    $.ajax({
                        async: false,
                        url: "/pfo/post/updateCommentCountByQuestionId",
                        dataType: "json",
                        data: "questionId=" + $('.result-question-box').attr("data-id"),
                        success: function (data) {
                        }
                    })
                    history.go(0)
                }
            }
        })
    })

    <!--回复框及回复事件-->
    var nums = 0
    var reply_id = ""
    <!-- 回复框的显示与隐藏-->
    //动态绑定click事件
    $("body").on("click", ".reply", function f() {
        // alert("userId:"+$(this).attr("data-id")+"commentId:"+$(this).attr("data-cmt-id"))
        reply_id = $(this).attr("data-cmt-id")
        $(".reply-box").remove()
        // $(".reply-box").remove()
        $(this).parent().parent().parent().append("            <div class=\"reply-box js-sub-cmt-list\" style=\"\">\n" +
            "                                    <div id=\"jsCpn_183_component_13\" class=\" \">\n" +
            "                                        <div class=\"js-tips\" style=\"display: none;\"></div>\n" +
            "                                        <div class=\"js-content\" style=\"\"></div>\n" +
            "                                        <div class=\"js-pager-box\" style=\"display:none;\">\n" +
            "                                        </div>\n" +
            "                                        <div class=\"js-reply-box\" style=\"\">\n" +
            "                                            <div id=\"jsCpn_184_component_10\" class=\" reply-editbox clearfix\"\n" +
            "                                                 style=\"margin-top:10px;\">\n" +
            "                                                <div class=\"reply-write\"><textarea placeholder=\"回复@" + $(this).attr("data-title") + "\"\n" +
            "                                                                                   class=\"reply-input reply-input-textarea js-ipt\"\n" +
            "                                                                                   style=\"resize: none; height: 32px; width: 541px;\"></textarea>\n" +
            "                                                </div>\n" +
            "                                                <div class=\"reply-action clearfix\"><a href=\"javascript:void(0);\"\n" +
            "                                                                                      class=\"icon-emotion js-reply-emoji\"></a><a\n" +
            "                                                        class=\"commentReply btn btn-primary reply-btn js-reply\"\n" +
            "                                                        href=\"javascript:void(0);\">回复</a></div>\n" +
            "                                            </div>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                </div>")
    })
    //    开始评论,触发评论事件(userId,commentId(replyId),commentContent)

    $("body").on("click", ".commentReply", function () {
        // alert($(".reply-write").children(0).val())
        $.get({
            async: false,
            url: "/pfo/comment/addComment",
            dataType: "json",
            data: "userId=" + $.cookie("userId") + "&replyId=" + reply_id + "&commentContent=" + $(".reply-write").children(0).val(),
            success: function (data) {
                if (data.code == 0) {
                    alert("评论成功")
                    $.ajax({
                        async: false,
                        url: "/pfo/post/updateCommentCountByQuestionId",
                        dataType: "json",
                        data: "questionId=" + $('.result-question-box').attr("data-id"),
                        success: function (data) {
                        }
                    })
                    history.go(0)
                }
            }
        })
    })

    $("body").on("click", "#reply", function () {
        $(".replyBack").removeAttr("hidden")
        $(".w-e-text").focus()
    })

    <!-- 添加回复数-->

    /*异步获取评论数据*/
    function getComments(qId) {
        $.get({
            async: false,
            url: "/pfo/comment/queryQsCommentFirstLevel",
            dataType: "json",
            data: "questionId=" + qId,
            success: function (data) {
                showFirstComments(data)
            }
        })
        $.get({
            async: false,
            url: "/pfo/comment/queryQsCommentSecondLevel",
            dataType: "json",
            data: "questionId=" + qId,
            success: function (data) {
                showSecondComments(data)
            }
        })

        $.get({
            async: false,
            url: "/pfo/comment/queryQsCommentThirdLevel",
            dataType: "json",
            data: "questionId=" + qId,
            success: function (data) {
                showThirdComments(data)
            }
        })
    }

    <!--显示一级评论,即帖子的评论-->
    function showFirstComments(data) {
        var html = "<div class=\"module-head clearfix\">\n" +
            "                        \n" +
            "                        <h4 id=\"numOfComments\"></h4>\n" +
            "                        <div id=\"jsCpn_8_component_0\" class=\"reply-rank-oprt btn-group btn-group-sm\">\n" +
            "                            <button class=\"btn btn-default dropdown-toggle\"><span class=\"js-txt \" title=\"默认排序\"><i\n" +
            "                                    class=\"ico-discuss-reply ico-earlier\"></i>默认排序</span><span class=\"caret\"></span>\n" +
            "                            </button>\n" +
            "                        </div>\n" +
            "                        <a id=\"reply\" style='margin-top: -10px;' class=\"btn btn-primary float-right nc-req-auth js-post-replay\" href=\"javascript:void(0);\"><i\n" +
            "                                class=\"ico-submit\"></i>添加解析</a>\n" +
            "                    </div>"
        $(".post-back").html(html)
        for (var i = 0; i < data.data.length; i++) {
            nums++
            var name = ""
            var userlogo = ""
            $.get({
                async: false,
                url: "/pfo/user/queryUsers",
                dataType: "json",
                data: "userId=" + data.data[i].userId,
                success: function (xx) {
                    name = xx.data[0].userName
                    userlogo = xx.data[0].userPhoto
                }
            })
            html += "<div class=\"answer-list-item clearfix\"\n" +
                "                         data-mark-info=\"作者："
            html += name + "\">\n" +
                "                        <div class=\"answer-content clearfix\"><a class=\"answer-head\" data-card-uid=\"840135486\"\n" +
                "                                                                href=\"/profile/840135486\"\n" +
                "                                                                data-card-index=\"24\"><img style=\"width: 100%;height: 100%;\" alt=\"头像\"\n" +
                "                                                                                          src=\""
            html += userlogo + "\"></a>\n" +
                "                            <div class=\"answer-detail\"><p><a href=\"/profile/840135486\"\n" +
                "                                                             data-card-uid=\"840135486\"\n" +
                "                                                             class=\"js-copy-tip level-color-7 answer-name\"\n" +
                "                                                             data-card-index=\"25\">"
            html += name +
                "</a> <a\n" +
                "                                    href=\"/user/authentication\" class=\"\" data-title=\"\"\n" +
                "                                    data-tips-index=\"8\"><img class=\"identity-icon\" data-identity=\"1\"\n" +
                "                                                             src=\"//static.nowcoder.com/nc/image/identity/5.png\"\n" +
                "                                                             data-title=\"\" data-tips-index=\"9\"></a> <a\n" +
                "                                    href=\"javascript:void(0);\"><img class=\"identity-icon\"\n" +
                "                                                                    src=\"//uploadfiles.nowcoder.com/images/20190708/56_1562592127316_5889949873E3E23C8A0EC0CC8F6042A8\"></a><span\n" +
                "                                    class=\"post-floor\">" + (i + 1) + "#</span></p>\n" +
                "                                <div class=\"answer-brief nc-post-content\">"
            html += data.data[i].commentContent +
                "</div>\n" +
                "                                <div class=\"answer-legend js-action\"><span\n" +
                "                                        class=\"answer-time\">发表于 "
            html += data.data[i].deliverTime +
                "</span><a href=\"javascript:void(0);\"\n" +
                "                                                                                             class=\"reply js-cmt-action\"\n" +
                "                                                                                             data-action=\"reply\" data-cmt-id='"
            html += data.data[i].commentId +
                "' data-id='"
            html += data.data[i].userId +
                "' data-title='"
            html += name +
                "'>回复(0)</a><a\n" +
                "                                        href=\"javascript:void(0);\" class=\"js-cmt-action\" data-action=\"like\">赞(0)</a><a\n" +
                "                                        href=\"javascript:void(0);\" class=\"\" data-action=\"share\">分享</a><a\n" +
                "                                        href=\"javascript:void(0);\" class=\"js-cmt-action click-del\" data-action=\"report\">举报</a>\n" +
                "                                </div>\n"
            html += "<div class='secondLevelComments' id='"
            html += data.data[i].commentId + "'><div>"
            html += "<div class='thirdLevelComments' id='"
            html += data.data[i].commentId + "'><div>" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>" +
                "                    </div>" +
                "                    </div>" +
                "                    </div>"
        }
        $(".post-back").html(html)
    }

    <!--显示二级评论-->
    function showSecondComments(data) {
        var html = ""
        for (var i = 0; i < data.data.length; i++) {
            html = ""
            nums++
            var replyId = ""
            var name = ""
            replyId = data.data[i].replyId
            $.get({
                async: false,
                url: "/pfo/user/queryUsers",
                dataType: "json",
                data: "userId=" + data.data[i].userId,
                success: function (xx) {
                    name = xx.data[0].userName
                }
            })
            html += "<div class=\"reply-list-item js-copy-mark\">\n" +
                "                <div class=\"reply-main clearfix\">\n" +
                "                <div class=\"reply-person\" style=\"margin-right:5px;\"><a href=\"/profile/826546\"\n" +
                "            data-card-index=\"41\">"
            html += name +
                "</a> <a\n" +
                "            href=\"/user/authentication\" class=\"\"></a> <a href=\"javascript:void(0);\"></a>：\n" +
                "                </div>\n" +
                "                <div class=\"reply-content\">"
            html += data.data[i].commentContent +
                "                </div>\n" +
                "                </div>\n" +
                "                <div class=\"answer-legend reply-info js-sub-action\"><span class=\"reply-time\">"
            html += data.data[i].deliverTime +
                "</span><a\n" +
                "            href=\"javascript:void(0);\" id='a"
            html += data.data[i].userId +
                "' class=\"reply secondC reply-answer js-cmt-action\" data-action=\"reply\" data-cmt-id='"
            html += data.data[i].commentId +
                "' data-id='"
            html += data.data[i].userId +
                "' data-title='"
            html += name +
                "'>回复</a><a\n" +
                "            href=\"javascript:void(0);\" class=\"reply-like js-cmt-action\" data-action=\"like\">赞(0)</a><a\n" +
                "            href=\"javascript:void(0);\" class=\"js-cmt-action click-del\" data-action=\"report\">举报</a></div>\n" +
                "            <div class=\"js-sub-reply\" style=\"display: none;\"></div>\n" +
                "                </div>" +
                "  <div class=\"thirdLevelComments\" id=\""
            html += data.data[i].commentId +
                "\">\n" +
                "    </div>"

            document.getElementById(replyId).innerHTML = document.getElementById(replyId).innerHTML + html
        }
    }

    <!--显示三级评论-->
    function showThirdComments(data) {
        var html = ""
        for (var i = 0; i < data.data.length; i++) {
            html = ""
            nums++
            var replyId = ""
            var name = ""
            var secname = ""
            var ss = ""
            replyId = data.data[i].replyId
            $.get({
                async: false,
                url: "/pfo/user/queryUsers",
                dataType: "json",
                data: "userId=" + data.data[i].userId,
                success: function (xx) {
                    name = xx.data[0].userName
                }
            })
            $.get({
                async: false,
                url: "/pfo/comment/queryUserId",
                dataType: "json",
                data: "replyId=" + replyId,
                success: function (dd) {
                    secname = dd.data[0].userId
                }
            })
            //用于获取id为x(a1\2\3)的标签的data-title(所回复的用户名)
            var x = "a" + secname
            if (document.getElementById(x)) {
                ss = $("#" + x + "").attr("data-title")
            }
            html += "<div class=\"reply-list-item js-copy-mark\">\n" +
                "                <div class=\"reply-main clearfix\">\n" +
                "                <div class=\"reply-person\" style=\"margin-right:5px;\"><a href=\"/profile/826546\"\n" +
                "        class=\"js-copy-tip level-color-8\"\n" +
                "            data-card-index=\"41\">"
            html += name + "回复" + ss +
                "</a> <a\n" +
                "            href=\"/user/authentication\" class=\"\"></a> <a href=\"javascript:void(0);\"></a>：\n" +
                "                </div>\n" +
                "                <div class=\"reply-content\">"
            html += data.data[i].commentContent +
                "                </div>\n" +
                "                </div>\n" +
                "                <div class=\"answer-legend reply-info js-sub-action\"><span class=\"reply-time\">"
            html += data.data[i].deliverTime +
                "</span><a\n" +
                "            href=\"javascript:void(0);\" class=\"reply reply-answer js-cmt-action\" data-action=\"reply\" data-cmt-id='"
            html += data.data[i].commentId +
                "' data-id='"
            html += data.data[i].userId +
                "' data-title='"
            html += name +
                "'>回复</a><a\n" +
                "            href=\"javascript:void(0);\" class=\"reply-like js-cmt-action\" data-action=\"like\">赞(0)</a><a\n" +
                "            href=\"javascript:void(0);\" class=\"js-cmt-action click-del\" data-action=\"report\">举报</a></div>\n" +
                "            <div class=\"js-sub-reply\" style=\"display: none;\"></div>\n" +
                "                </div>" +
                "  <div class=\"thirdLevelComments\" id=\""
            html += data.data[i].commentId +
                "\">\n" +
                "    </div>"

            document.getElementById(replyId).innerHTML = document.getElementById(replyId).innerHTML + html
        }
    }

    <!--计算回帖数-->
    $("#numOfComments").html("共" + nums + "条讨论")
</script>


<script>
    var E = window.wangEditor
    var replyEditor = new E('#commentEditor')
    //开启debug模式
    replyEditor.customConfig.debug = true;
    // 关闭粘贴内容中的样式
    replyEditor.customConfig.pasteFilterStyle = false
    // 忽略粘贴内容中的图片
    replyEditor.customConfig.pasteIgnoreImg = true
    // 上传图片到服务器
    replyEditor.customConfig.uploadFileName = 'myFile'; //设置文件上传的参数名称
    replyEditor.customConfig.uploadImgServer = '/pfo/uploadImg'; //设置上传文件的服务器路径
    replyEditor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024; // 将图片大小限制为 3M
    //自定义上传图片事件
    replyEditor.customConfig.uploadImgHooks = {
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            // 插入图片到editor
            editor.cmd.do('insertHtml', '<img alt="图片走丢了*_*" src="http://' + result.data[0] + '" style="max-width:100%;"/>')
        },
        before: function (xhr, replyEditor, files) {
            console.log("正在上传...请稍候");
        },
        success: function (xhr, replyEditor, result) {
            console.log("上传成功");
        },
        fail: function (xhr, replyEditor, result) {
            console.log("上传失败,原因是" + result);
        },
        error: function (xhr, replyEditor) {
            console.log("上传出错");
        },
        timeout: function (xhr, replyEditor) {
            console.log("上传超时");
        }
    }
    replyEditor.create()
    replyEditor.$textElem.attr('contenteditable', true)


</script>
</body>
</html>