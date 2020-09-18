<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>试卷详情</title>

    <script src="/pfo/js/jquery-3.4.1.js"></script>
    <script src="/pfo/js/bootstrap.js"></script>
    <script src="/pfo/js/jquery.cookie.js"></script>
    <script src="/pfo/js/exam/syalert.min.js"></script>
    <script src="/pfo/js/common/baseTopNavigation.js"></script>
    <script src="/pfo/js/common/baseTopNavigationJS.js"></script>
    <link rel="stylesheet" href="/pfo/css/common/baseTopNavigation.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/exam/paper_detail.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/exam/syalert.min.css" type="text/css">
    <link rel="stylesheet" href="/pfo/css/global.css" type="text/css">

</head>
<body class="bg-light">
<div class="container" id="paper-box">
    <ol class="breadcrumb bg-white">
        <li class="breadcrumb-item"><a href="/pfo/">首页</a></li>
        <li class="breadcrumb-item"><a href="/pfo/html/exam/exam_bank.html">公司历届套题</a></li>
        <li class="breadcrumb-item active">${paperInfo.paperName}</li>
    </ol>

    <div class="bg-white" id="detail-box">
        <div class="paper-wrap">
            <div id="paper-content">
                <h5>${paperInfo.paperName}</h5>
                <img class="paper-img float-left img-thumbnail" src="${paperInfo.paperImg}" alt="">
                <img id="facePhoto" src="/pfo/img/logo/cancelFocus.svg" width="90" height="120">
                <ul class="paper-detail">
                    <li>客观题：单选2道,不定项选择2道,填空11道</li>
                    <li>主观题：问答2道</li>
                    <li>完成时间： 120分钟</li>
                    <li>总分： 100分</li>
                </ul>
            </div>

            <div id="paper-summary" class="card d-flex justify-content-center bg-light">
                <div class="card-body">
                    <ol>
                        <li>本试卷由企业提供，用户可免费申请练习，成绩优秀用户有机会获得企业内推机会</li>
                        <li>你已开启本类试卷使用权限，可无限制练习所有企业提供的真题。</li>
                        <li>未经许可，任何第三方不得以任何理由私自使用，违者必究。</li>
                        <li class="paperNote">${paperInfo.paperDescription}</li>
                    </ol>
                </div>
            </div>

            <div class="button-box">
                <button id="face-detect" class="btn btn-sm bg-info" data-toggle="modal" data-target="#faceModal">获取人脸</button>
                <button type="submit" id="start-train" class="btn bg-info d-flex justify-content-center"><a
                        href="/pfo/exam/queryPaperQuestions?currPage=1&paperId=${paperInfo.paperId}&questionListId=${paperInfo.questionListId}">开始练习</a></button>
            </div>

        </div>
    </div>

    <div class="show-list">
        <div class="bg-white" id="ranking">
            <div class="list-group">
                <div  class="list-group-item list-group-item-action">热门排行榜</div>
                <li class="list-group-item list-group-item-danger paper-list">
                    <img src="" alt="oop" class="float-left img-thumbnail" style="width: 48px;height: 48px;margin-right:5px">
                    <a href="#"></a>
                </li>
                <li class="list-group-item list-group-item-warning paper-list">
                    <img src="" alt="oop" class="float-left img-thumbnail" style="width: 48px;height: 48px;margin-right:5px">
                    <a href="#"></a>
                </li>
                <li class="list-group-item list-group-item-info paper-list">
                    <img src="" alt="oop" class="float-left img-thumbnail" style="width: 48px;height: 48px;margin-right:5px">
                    <a href="#"></a>
                </li>
                <li class="list-group-item list-group-item-success paper-list">
                    <img src="" alt="oop" class="float-left img-thumbnail" style="width: 48px;height: 48px;margin-right:5px">
                    <a href="#"></a>
                </li>
                <li class="list-group-item list-group-item-secondary paper-list">
                    <img src="" alt="oop" class="float-left img-thumbnail" style="width: 48px;height: 48px;margin-right:5px">
                    <a href="#"></a>
                </li>

            </div>
        </div>

        <div class="bg-white" id="user-list">
            <li>他们也在做</li>
        </div>
    </div>

    <!-- 获取人脸弹窗 -->
    <!-- 模态框 -->
    <div class="modal fade" id="faceModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <script src="/pfo/js/tracking/tracking-min.js"></script>
                <script src="/pfo/js/tracking/face-min.js"></script>
                <script src="/pfo/js/tracking/eye-min.js"></script>
                <script src="/pfo/js/tracking/mouth-min.js"></script>
                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">请将脸对准方框</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    <div class="catchWindow" style="margin: auto">
                        <video id="video" preload autoplay loop muted></video>
                        <canvas id="canvas" width="300" height="400"></canvas>
                        <canvas id="canvas1" width="300" height="400"></canvas>
                    </div>
                    <button onClick="faceTracking()">获取照片</button>
                </div>

                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                </div>

            </div>
        </div>
    </div>

    <canvas id="shortCut" width="300" height="400" hidden></canvas>
</div>

<script>
    var SortKey = {
        key:'ct',
        type:'desc'
    }

    $(document).ready(function () {
        getPaperList()
        var note = ''
        var paperNote = "${paperInfo.paperDescription}".split("#")[0].split(";")

        if(paperNote.length >1){
            for (var i = 0; i < paperNote.length; i++) {
                note += '<li>'+paperNote[i]+'</li>'
            }

            $('.paper-detail').html(note)
        }

    })


    function getPaperList() {
        $.get({
            url: "/pfo/exam/queryAllPapers",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: 'currPage=1&pageSize=5&orderKey=' + SortKey.key + '&orderType=' + SortKey.type,
            success: function (data) {
                showPapers(data)
            }
         })
    }

    function showPapers(data) {
        var pList = $('.paper-list')
        $('.paper-list').each(function (i,v) {
            $(this).children("a").text(data.dataList[i].paperName)
            $(this).children("img").attr("src",data.dataList[i].paperImg)
            $(this).children("a").attr("href","/pfo/exam/queryPaperById?paperId="+data.dataList[i].paperId)
        })

    }

    var saveArray = {};
    function faceTracking() {
        var video = document.getElementById('video');
        var canvas = document.getElementById('canvas');
        var context = canvas.getContext('2d');

        var tracker = new window.tracking.ObjectTracker('face');
        tracker.setInitialScale(4);
        tracker.setStepSize(1.1);
        tracker.setEdgesDensity(0.1);

        this.trackerTask = window.tracking.track('#video', tracker, {camera: true});

        tracker.on('track', function (event) {
            context.clearRect(0, 0, canvas.width, canvas.height);

            event.data.forEach(function (rect) {
                context.strokeStyle = '#fff';
                context.strokeRect(rect.x, rect.y, rect.width, rect.height);
                //context.font = '11px Helvetica';
                //context.fillStyle = "#fff";
                //context.fillText('x: ' + rect.x + 'px', rect.x + rect.width + 5, rect.y + 11);
                //context.fillText('y: ' + rect.y + 'px', rect.x + rect.width + 5, rect.y + 22);
                //console.log(rect.x, rect.width, rect.y, rect.height);
                saveArray.x = rect.x;
                saveArray.y = rect.y;
                saveArray.width = rect.width;
                saveArray.height = rect.height;
            });
        });
        var recognizeFace = setInterval(function () {
            console.log(saveArray)
            //每4000毫秒检测一次若人脸出现在相框中自动截图
            if (saveArray.x > 60 &&
                saveArray.x + saveArray.width < 260 &&
                saveArray.y > 100 &&
                saveArray.y + saveArray.height < 320 &&
                saveArray.width < 180
                && saveArray.height < 240) {
                getPhoto()
                for (var key in saveArray) {
                    delete saveArray[key];
                }
                clearInterval(recognizeFace)
                saveFaceImg()
                destroyed()
                $("#faceModal").modal('hide');
            }
        }, 4000);

        function destroyed() {
            // 停止侦测
            this.trackerTask.stop()
            // 关闭摄像头
            document.getElementById('video').srcObject.getTracks()[0].stop()
        }

        //拍照
        function getPhoto() {
            context2.drawImage(video, 160, 80, 320, 380, 0, 0, 210, 240); //将video对象内指定的区域捕捉绘制到画布上指定的区域，实现拍照。
        }

        var canvas1 = document.getElementById('canvas1');
        var context1 = canvas1.getContext('2d');
        var can = document.getElementById('shortCut');
        var context2 = can.getContext('2d');
        context1.strokeStyle = "#69fff1";
        context1.moveTo(60, 100);
        context1.lineTo(240, 100);
        context1.lineTo(240, 300);
        context1.lineTo(60, 300);
        context1.lineTo(60, 100);
        context1.stroke();


        var img = document.getElementById("img")

        //将canvas转化为图片
        function convertCanvasToImage(canvas) {
            var image = new Image();
            image.src = canvas.toDataURL("image/png");
            return image;
        }

        //保存图片
        var imgSrc = ''

        function saveFaceImg() {
            //var photoImg = document.createElement("img");
            //photoImg.src = convertCanvasToImage(can).src;
            //img.appendChild(photoImg);
            //获取到转化为base64的图片地址
            imgSrc = convertCanvasToImage(can).src
            sessionStorage['faceImage1'] = imgSrc
            document.getElementById('facePhoto').src=imgSrc
            console.log(convertCanvasToImage(can).src);
        }

    };

</script>
<!--底部栏-->
<script src="/pfo/js/common/footer.js" type="application/javascript"></script>
<link rel="stylesheet" href="/pfo/css/common/footer.css" type="text/css">
</body>

</html><!---->