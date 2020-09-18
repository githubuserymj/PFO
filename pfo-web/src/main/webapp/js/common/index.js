$(document).ready(function () {
    $.ajax({
        url:"/pfo/post/queryByRequiredOrder?order=2",
        success:function (resultData) {
            if(resultData.code===0){
                var hotPostList=resultData.data;
                var length=hotPostList.length;
                if(length>10){
                    length=10;//热帖最多显示10篇
                }
                for(var i=0;i<length;i++){
                    var postId=hotPostList[i].postId;
                    var postTitle=hotPostList[i].postTitle;
                    var deliverTime=hotPostList[i].deliverTime;
                    var viewCount=hotPostList[i].viewCount;
                    var commentCount=hotPostList[i].commentCount;
                    var userName=hotPostList[i].userName;
                    var html='<div class="list-group-item" href="three-column.html">\n' +
                        '                            <strong>'+userName+'：</strong>\n' +
                        '                            <a href="/pfo/html/discussion/pfo_postInfo.html?postId='+postId+'"><strong>'+postTitle+'</strong></a>\n' +
                        '                            <div>\n' +
                        '                                <span style="font-size: 15px">浏览量数：'+viewCount+'</span>\n' +
                        '                                <span style="font-size: 15px">评论数：'+commentCount+'</span>\n' +
                        '                                <span style="font-size: 15px">时间：'+deliverTime+'</span>\n' +
                        '                            </div>\n' +
                        '                        </div>';
                    $(".hotPost").append(html)
                }
            }
        }
    })

    /**
     * 获取推荐招聘信息  后台推荐算法待实现
     */
    $.ajax({
        url: "/pfo/recruitment/queryRecruitmentByRecommend",
        type: "get",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var recruitment = data.data;
            var html = '';
            $.each(recruitment, function (i, value) {
                html += '   <div style="float: left; margin: 10px; overflow: hidden">' +
                    '           <a href="/pfo/html/recruit/recruit_detail.html?id='+value.recruitmentId+'"><img src="'+value.recruitmentImg+'" alt="" style="width: 150px"></a>\n' +
                    '       </div>';
            })

            $(".hotRecruitment").html(html);

        }
    })

})