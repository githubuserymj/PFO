<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>试题</title>
    <link rel="stylesheet" href="/pfo/css/bootstrap.css" type="text/css">
    <script src="/pfo/js/jquery-3.4.1.js"></script>
    <script src="/pfo/js/jquery.cookie.js"></script>
    <script src="/pfo/js/bootstrap.js"></script>
    <script src="/pfo/js/common/baseTopNavigation.js"></script>
    <script src="/pfo/js/common/baseTopNavigationJS.js"></script>
    <script src="/pfo/js/wangEditor.js"></script>
    <script src="/pfo/js/exam/timer.jquery.js"></script>
    <script src="/pfo/js/codeMirror/codemirror.js"></script>
    <script src="/pfo/js/codeMirror/modes/markdown.js"></script>
    <script src="/pfo/js/codeMirror/modes/clike.js"></script>
    <script src="/pfo/js/codeMirror/modes/sublime.js"></script>
    <script src="/pfo/js/codeMirror/modes/comment.js"></script>
    <script src="/pfo/js/codeMirror/modes/active-line.js"></script>
    <script src="/pfo/js/codeMirror/modes/show-hint.js"></script>
    <script src="/pfo/js/codeMirror/modes/foldcode.js"></script>
    <script src="/pfo/js/codeMirror/modes/foldgutter.js"></script>
    <script src="/pfo/js/codeMirror/modes/closebrackets.js"></script>
    <script src="/pfo/js/codeMirror/modes/comment-fold.js"></script>
    <script src="/pfo/js/codeMirror/modes/indent-fold.js"></script>
    <script src="/pfo/js/codeMirror/modes/brace-fold.js"></script>
    <script src="/pfo/js/tracking/dat.gui.min.js"></script>
    <script src="/pfo/js/tracking/tracking-min.js"></script>
    <script src="/pfo/js/tracking/face-min.js"></script>
    <script src="/pfo/js/tracking/mouth-min.js"></script>
    <script src="/pfo/js/tracking/eye-min.js"></script>
    <link rel="stylesheet" href="/pfo/css/wangEditor.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/codeMirror/codemirror.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/codeMirror/foldgutter.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/codeMirror/fullscreen.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/codeMirror/eclipse.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/codeMirror/lint.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/codeMirror/show-hint.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/common/baseTopNavigation.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/exam/question.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/global.css" type="text/css">

    <style>
        video, #Canvas {
            position: absolute;
            z-index: -10;
            display: none;
        }

    </style>
</head>

<body>

<div class="container">
    <div class="question-box bg-white">


            <video id="video" preload autoplay loop muted></video>
            <canvas id="Canvas" width="300px" height="400px"></canvas>


        <div class="question-progress">
            <div class="progress">
                <div class="progress-bar bg-success progress-bar-striped progress-bar-animated"
                     role="progressbar" aria-valuenow="25" aria-valuemin="0"
                     aria-valuemax="100">
                </div>
            </div>
            <span class="progress-nums"></span>
            <div class="progress-time" title="暂停">
                <a class="ico-time-control" onclick="stop()"></a>
                <span class="time-text" style="font-size: 20px;">00:00:00</span>
            </div>
        </div>
        <div class="question-type"></div>
        <div class="question-main">
            <div class="question-content">
                <!--题目-->
                <h3 class="question-title"></h3>
                <!--试题模板-->
                <div class="question-sheet"></div>
            </div>
            <%--工具栏--%>
            <div class="question-action">
                <div class="question-operation">
                            <span class="question-operation-item">
                                <i class="ico-collect"><img src="/pfo/img/tools/收藏.png"></i>
                                <a href="javascript:void(0);" class="question-collect">收藏本题</a>
                            </span>
                    <span class="question-operation-item">
                                <i class="ico-mark"><img src="/pfo/img/tools/标记.png"></i>
                                <a href="javascript:void(0);" class="question-mark">标记一下</a>
                            </span>
                    <span class="question-operation-item">
                                <i class="ico-call-help"><img src="/pfo/img/tools/问题求助.png"></i>
                                <a href="javascript:void(0);" class="question-call-help">场外求助</a>
                            </span>
                </div>
                <div class="question-next">
                    <input type="submit" id="finishPaper" class="btn btn-warning" value="提前交卷">
                    <input type="submit" id="btn-next" class="btn btn-info" value="下一题">
                </div>
            </div>
        </div>
        <div class="answer-sheet">
            <button id="card-fold" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="collapse"
                    data-target="#demo">收起答题卡
            </button>
            <div id="demo" class="collapse show">
                <ul class="answer-sheet-num">

                </ul>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        /* 开始计时*/
        start()
        // 右键禁用
        securityHandler()

        $('.item1').addClass("active")

        var pageInfo = {
            currPage:${question.currPage},
            totalSize:${question.totalSize}
        }

        /*获取题目内容*/
        var content = JSON.parse('${question.dataList[0].questionContent}')

        /*获取试题类型*/
        var type = '${question.dataList[0].questionType}'
        initProgress(pageInfo)
        initModel(type, content)
    })

    function securityHandler() {
        // 右键禁用
        if (document.addEventListener) {
            document.addEventListener("contextmenu", function(e) {
                e.preventDefault();
            }, false);
        } else {
            document.attachEvent("contextmenu", function(e) {
                e.preventDefault();
            });
        }

        $(window).bind('beforeunload', function() {
            return "考试正在进行中...";
        });
    }

    /*日期格式化*/
    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    var st = new Date().Format("yyyy-MM-dd HH:mm:ss")

    /*初始化问题模板*/
    function initModel(type, content) {
        $('.question-type').text(type)
        /*获取试题题目*/
        $('.question-title').text(content.title)
        /*初始化单选题模板*/
        if ("单选题" == type) {
            var sheet = ''
            for (var i = 0; i < content.options.length; i++) {
                sheet += '<div class="radio">'
                sheet += '<label><input type="radio" name="optradio" value="' + content.options[i].alias + '">'
                sheet += content.options[i].alias + '. ' + content.options[i].text
                sheet += '</label></div>'
            }
            $('.question-sheet').html(sheet)
        }

        /*初始化不定向选择题模板*/
        if ("不定项选择题" == type) {
            var sheet = ''
            for (var i = 0; i < content.options.length; i++) {
                sheet += '<div class="form-check">'
                sheet += '<label class="form-check-label">'
                sheet += '<input type="checkbox" class="form-check-input" name="cks" value="' + content.options[i].alias + '">'
                sheet += content.options[i].alias + '. ' + content.options[i].text
                sheet += '</label></div>'
            }
            $('.question-sheet').html(sheet)
        }

        /*初始化判断题模板*/
        if ("判断题" == type) {
            var sheet = ''
            sheet += '<div class="radio">'
            sheet += '<label><input type="radio" name="tfradio" value="对">对'
            sheet += '</label></div>'
            sheet += '<div class="radio">'
            sheet += '<label><input type="radio" name="tfradio" value="错">错'
            sheet += '</label></div>'
            $('.question-sheet').html(sheet)
        }

        /*初始化填空题模板*/
        if ("填空题" == type) {
            var sheet = ''
            for (var k = 1; k <= content.blank; k++) {
                sheet += '<div class="input-group mb-3">'
                sheet += '<div class="input-group-prepend">'
                sheet += '<span class="input-group-text">'
                sheet += k
                sheet += '</span></div><input id="blank' + k + '" type="text" name="blankInput" class="form-control" placeholder="填入第' + k + '空的答案"></div>'
            }

            $('.question-sheet').html(sheet)
        }

        /*初始化问答题模板*/
        if ("问答题" == type) {
            $('.question-sheet').html('<div class="design-answer-box" id="editor"></div><button id="test" class="btn bg-danger">测试</button>')
            createEditor()
        }

        /*初始化编程题模板*/
        if ("编程题" == type) {
            $('.question-sheet').html('<div class="design-answer-box" id="editor"></div><button id="test" class="btn bg-danger">测试</button> ')
            createCodeEditor()
        }
    }


    /*异步获取试题题*/

    var questionId =${question.dataList[0].questionId}
    var type = '${question.dataList[0].questionType}'
    var pageInfo = {
        currPage: ${question.currPage},
        totalSize: ${question.totalSize}
    }

    function nextQuestion(page) {
        /*加载下一题前保存用户答案*/
        setUserAnswers(questionId, type)
        var params = {
            currPage: page,
            ids: '${ids}'
        }

        $('.page-item').removeClass("active");
        $('.item' + page + '').addClass("active")

        $.get({
            url: "/pfo/exam/nextQuestion",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: params,
            success: function (data) {
                questionId = data.dataList[0].questionId
                type = data.dataList[0].questionType
                var content = JSON.parse(data.dataList[0].questionContent)
                pageInfo = {
                    currPage: data.currPage,
                    totalSize: data.totalSize
                }
                initProgress(pageInfo)
                initModel(type, content)
            }
        })
    }

    $('#btn-next').click(function () {
        /*加载下一题前保存用户答案*/
        setUserAnswers(questionId, type)
        nextQuestion(pageInfo.currPage + 1)

    })

    /*创建代码编辑器*/
    var myCodeMirror

    function createCodeEditor() {
        var el = document.getElementById("editor");
        var version = "// version: Java\n\n";
        var codeAreaTip = "public class Test {\n// please edit your code here:\n";
        var codeStart = "// code start\n\n";
        var codeEnd = "// code end\n\n";
        var codeTip = " \n//This function is the entry of this program and\n//it must be return your answer of current question.\n\n";
        var code = "    public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n    }\n}";
        var initValue = codeAreaTip + version + codeStart + codeEnd + codeTip + code;
        myCodeMirror = CodeMirror(el, {
            mode: "text/x-java",// 语言模式
            theme: "eclipse",// 主题
            keyMap: "sublime",// 快键键风格
            lineNumbers: true,// 显示行号
            smartIndent: true, //智能缩进
            indentUnit: 4, // 智能缩进单位为4个空格长度
            indentWithTabs: true,  // 使用制表符进行智能缩进
            lineWrapping: true,// 在行槽中添加行号显示器、折叠器、语法检测器
            gutters: ["CodeMirror-foldgutter"],
            foldGutter: true, // 启用行槽中的代码折叠
            autofocus: true,//自动聚焦
            matchBrackets: true,// 匹配结束符号，比如"]、}"
            autoCloseBrackets: true, // 自动闭合符号
            styleActiveLine: true, // 显示选中行的样式
        });
        // 设置初始文本，这个选项也可以在fromTextArea中配置
        myCodeMirror.setOption("value", initValue);
        // 编辑器按键监听
        myCodeMirror.on("keypress", function () {
            // 显示智能提示
            myCodeMirror.showHint(); // 注意，注释了CodeMirror库中show-hint.js第131行的代码（阻止了代码补全，同时提供智能提示）
        });
        var test = document.getElementById("test");
        test.onclick = function () {
            var value = myCodeMirror.getValue();
            $.post({
                url: "/pfo/getJavaCompiler",
                data: {"originCode": value},
                success: function (data) {
                    if (data.code == 0) {
                        var runInfo = data.data;
                        var resultInfo = ''
                        resultInfo += "<div class=\"card\"><div class=\"card-body\">"
                        resultInfo += "编译耗时：" + runInfo.compilerTakeTime + "\t编译信息：" + runInfo.compilerMessage + "\t编译状态：" + runInfo.compilerSuccess
                        resultInfo += "\n运行结果：\n" + runInfo.runResult
                        resultInfo += "\n运行耗时：" + runInfo.runTakeTime + "\t运行状态：" + runInfo.runSuccess
                        resultInfo += '</div></div>'
                        $('.design-answer-box').after(resultInfo)

                    } else {
                        alert(data.message);
                    }
                }
            })


        };
    }

    /*创建富文本编辑器*/
    var editor

    function createEditor() {
        var E = window.wangEditor
        editor = new E('#editor')
        //开启debug模式
        editor.customConfig.debug = true;
        // 关闭粘贴内容中的样式
        editor.customConfig.pasteFilterStyle = false
        // 忽略粘贴内容中的图片
        editor.customConfig.pasteIgnoreImg = true
        // 上传图片到服务器
        editor.customConfig.uploadFileName = 'myFile'; //设置文件上传的参数名称
        editor.customConfig.uploadImgServer = '/pfo/uploadImg'; //设置上传文件的服务器路径
        editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024; // 将图片大小限制为 3M
        //自定义上传图片事件
        editor.customConfig.uploadImgHooks = {
            customInsert: function (insertImg, result, editor) {
                // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
                // 插入图片到editor
                editor.cmd.do('insertHtml', '<img alt="图片走丢了*_*" src="http://' + result.data[0] + '" style="max-width:100%;"/>')
            },
            before: function (xhr, editor, files) {
                console.log("正在上传...请稍候");
            },
            success: function (xhr, editor, result) {
                console.log("上传成功");
            },
            fail: function (xhr, editor, result) {
                console.log("上传失败,原因是" + result);
            },
            error: function (xhr, editor) {
                console.log("上传出错");
            },
            timeout: function (xhr, editor) {
                console.log("上传超时");
            }
        }
        editor.create()
        editor.$textElem.attr('contenteditable', true)
        $('#test').click(function () {
            alert(editor.txt.text())
        })
    }

    /*初始进度条*/
    function initProgress(pageInfo) {
        if (pageInfo.currPage >= pageInfo.totalSize) {
            $('#btn-next').attr('disabled', "true")
            $('#btn-next').hide()
            $('#finishPaper').val('提交试卷')
            $('#finishPaper').addClass("btn-danger")
        } else {
            $('#btn-next').show()
            $('#finishPaper').removeClass("btn-danger")
            $('#btn-next').removeAttr("disabled")
        }

        $('.progress-nums').text('' + pageInfo.currPage + '/' + pageInfo.totalSize + '')
        var progress = Math.ceil((pageInfo.currPage / pageInfo.totalSize) * 100)
        $('.progress-bar').css("width", "" + progress + "%")
        $('.progress-bar').text('' + progress + '%')
    }

    var hour, minute, second;//时 分 秒
    hour = minute = second = 0;//初始化
    var millisecond = 0;//毫秒
    var int;

    function Reset()//重置
    {
        window.clearInterval(int);
        millisecond = hour = minute = second = 0;
        $('.time-text').text('00:00:00');
    }

    function start()//开始
    {
        int = setInterval(timer, 50);
    }

    function timer()//计时
    {
        millisecond = millisecond + 50;
        if (millisecond >= 1000) {
            millisecond = 0;
            second = second + 1;
        }
        if (second >= 60) {
            second = 0;
            minute = minute + 1;
        }

        if (minute >= 60) {
            minute = 0;
            hour = hour + 1;
        }
        $('.time-text').text('' + toDub(hour) + ':' + toDub(minute) + ':' + toDub(second) + '');

    }

    function stop()//暂停
    {
        window.clearInterval(int);
    }

    //补零
    function toDub(n) {
        return n < 10 ? "0" + n : "" + n;
    }

    /*初始答题卡*/
    var pages = ''
    for (var j = 1; j <= ${question.totalSize}; j++) {
        pages += '<li class="page-item item' + j + '"><a class="page-link" href="#" onclick="nextQuestion(' + j + ')">' + j + '</a></li>'
    }
    $('.answer-sheet-num').html(pages)


    $('#card-fold').click(function () {
        if ($(this).text() == "收起答题卡") {
            $(this).text("展开答题卡")
        } else {
            $(this).text("收起答题卡")
        }
    })

    /*保存用户答案*/
    var userAnswer = {}

    function setUserAnswers(qid, type) {

        if ("单选题" == type) {
            var temp = $('input[name="optradio"]:checked').val()
            userAnswer[qid] = temp
        } else if ("不定项选择题" == type) {
            var temp = new Array()
            $('input[name="cks"]:checked').each(function () {
                temp.push($(this).val());//向数组中添加元素
            });
            userAnswer[qid] = temp
        } else if ("填空题" == type) {
            var temp = new Array()
            $('input[name="blankInput"]').each(function () {
                temp.push($(this).val());//向数组中添加元素
            });
            userAnswer[qid] = temp
        } else if ("问答题" == type) {
            userAnswer[qid] = editor.txt.text()

        } else if ("编程题" == type) {
            userAnswer[qid] = myCodeMirror.getValue()
        } else if ("判断题" == type) {
            var temp = $('input[name="tfradio"]:checked').val()
            userAnswer[qid] = temp
        }
    }

    /*生成答题卡*/
    $('#finishPaper').click(function () {
        /*加载下一题前保存用户答案*/
        setUserAnswers(questionId, type)
        var sheet = JSON.stringify(genrateAnswerSheet())
        $.post({
            url: "/pfo/exam/finishExam",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data: sheet,
            success: function (data) {
                window.location.href = "/pfo/html/exam/paperAnalytic.html?examId=" +
                    data.examId + "&correctNum=" + data.correctNum + "&questionNum=" +
                    data.questionNum + "&consumingTime=" + data.consumingTime;

            }
        })
    })


    function genrateAnswerSheet() {
        var answerSheet = {
            userId:parseInt($.cookie("userId")),
            paperId:${paperId},
            finishTime: new Date().Format("yyyy-MM-dd HH:mm:ss"),
            totalTime: $('.time-text').text(),
            startTime: st

        }
        var questionIds = '${ids}'.split(",")

        for (var k = 0; k < questionIds.length; k++) {
            answerSheet['Q' + questionIds[k] + ''] = userAnswer[questionIds[k]]

        }

        return answerSheet
    }

</script>

<script>

    var CameraInit=(function(window,document,undefined){
        function MyCamera(videoDom,canvasDom) {
            this.mediaOpts = {
                audio: false,
                video: true,
                //video: { facingMode: "environment"} // 或者 "user"
                video: { width: 300, height: 400 }
                // video: { facingMode: { exact: "environment" } }// 或者 "user"
            }
            this.video=videoDom;
            this.canvas1=canvasDom;
            this.context1 = this.canvas1.getContext('2d');
            this.mediaStreamTrack = null;
            this.checkEnvironment();
        }
        MyCamera.prototype = {
            checkEnvironment: function() {
                window.URL = (window.URL || window.webkitURL || window.mozURL || window.msURL);
                if (navigator.mediaDevices === undefined) {
                    navigator.mediaDevices = {};
                }
                if (navigator.mediaDevices.getUserMedia === undefined) {
                    navigator.mediaDevices.getUserMedia = function(constraints) {
                        var getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
                        if (!getUserMedia) {
                            return Promise.reject(new Error('getUserMedia is not implemented in this browser'));
                        }
                        return new Promise(function(resolve, reject) {
                            getUserMedia.call(navigator, constraints, resolve, reject);
                        });
                    }
                }
            },
            // 回调
            successFunc: function(stream) {
                this.mediaStreamTrack = stream;
                this.video = document.querySelector('video');
                if ("srcObject" in this.video) {
                    this.video.srcObject = stream
                } else {
                    this.video.src = window.URL && window.URL.createObjectURL(stream) || stream
                }
                this.video.play();
            },
            errorFunc:function(err) {
                alert(err.name);
            },

            // 正式启动摄像头
            openMedia: function(){
                navigator.mediaDevices.getUserMedia(this.mediaOpts).then(this.successFunc.bind(this)).catch(this.errorFunc);
            },

            //关闭摄像头
            closeMedia: function(){
                var that=this;
                that.mediaStreamTrack.getVideoTracks().forEach(function (track) {
                    track.stop();
                    that.context1.clearRect(0, 0,that.context1.width, that.context1.height);//清除画布
                });
            },

            //截取视频并转为图片
            drawMediaAndToImg: function(){
                this.canvas1.setAttribute("width", this.video.videoWidth);
                this.canvas1.setAttribute("height", this.video.videoHeight);
                this.context1.drawImage(this.video, 0, 0, this.video.videoWidth, this.video.videoHeight);//显示在canvas中

                var imgSrc = this.canvas1.toDataURL('image/png')
                console.log(imgSrc)
                sessionStorage['faceImage2'] = imgSrc

            },
        };
        return MyCamera;
    })(window,document)

    window.MyCamera=new CameraInit(document.getElementById("video"), document.getElementById("Canvas"));

    setTimeout(function () {

        //开启
        window.MyCamera.openMedia()
        //拍照
        setTimeout(function () {
            window.MyCamera.drawMediaAndToImg()
            //关闭
            window.MyCamera.closeMedia()
        },1000*6)



    }, 1000*6);


</script>

<!--底部栏-->
<script src="/pfo/js/common/footer.js" type="application/javascript"></script>
<link rel="stylesheet" href="/pfo/css/common/footer.css" type="text/css">
</body>

</html>