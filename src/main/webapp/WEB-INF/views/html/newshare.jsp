<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ page isELIgnored="false" %>--%>
<c:set var="serverContext" value="${pageContext.request.contextPath}" ></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta name="format-detection" content="telephone=no, email=no">
    <link rel="stylesheet" href="../css/style.css"/>
    <script id="DS_PRE_JS" type="text/javascript"  src="http://cdn.datastory.com.cn/js/pre-ds-min.js?dsTid=b59d5cd1-2f48-4c76-8868-4f27ab46cb11">
    </script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="../resource/jquery.min.js"></script>
    <title>我在参加Weego全球限量活动，价值百万的福利你值得拥有！</title>
    <style type="text/css">
        @-webkit-keyframes shang{
            0%{-webkit-transform:translateY(0%);
                transform:translateY(0%);
                visibility:visible;opacity:1;}
            100%{
                -webkit-transform:translateY(-30%);
                transform:translateY(-30%);
                opacity:0;}
        }

        @-webkit-keyframes shang1{
            0%{-webkit-transform:translateY(700%);
                transform:translateY(700%);
                visibility:visible;opacity:0;}
            100%{-webkit-transform:translateY(0%);
                transform:translateY(0%);
                opacity:1;}
        }
        @-webkit-keyframes shang2{
            0%{-webkit-transform:translateY(130%);
                transform:translateY(130%);
                visibility:visible;opacity:0;}
            100%{-webkit-transform:translateY(0%);
                transform:translateY(0%);
                opacity:1;}
        }
        @-webkit-keyframes shang3{
            0%{-webkit-transform:translateY(10%);
                transform:translateY(10%);
                visibility:visible;opacity:0;}
            100%{-webkit-transform:translateY(0%);
                transform:translateY(0%);
                opacity:1;}
        }
        @-webkit-keyframes shang4{
            0%{-webkit-transform:translateY(30%);
                transform:translateY(30%);
                visibility:visible;opacity:0;}
            100%{-webkit-transform:translateY(0%);
                transform:translateY(0%);
                opacity:1;}
        }
        .page .page01{
            background-image: url(../resource/img/share/bg.jpg);
            height: 100%;
            background-size: cover;
        }

        .page .page01 .logo{
            width: 50%;
            position: absolute;
            top: 5%;
            left: 23%;
            opacity: 0;
            -webkit-animation:shang1 1s linear 0.2s 1 forwards;
        }
        .page .page01 .top{
            position: absolute;
            width: 90%;
            top: 17%;
            left: 25px;
            opacity: 0;
            -webkit-animation:shang2 1s linear 1.2s 1 forwards;
        }
        .page .page01 .middle{
            width: 90%;
            position: absolute;
            top:54%;
            left: 15px;
            opacity: 0;
            -webkit-animation:shang3 1s linear 2.2s 1 forwards;
        }
        .page .page01 .end{
            position: absolute;
            bottom: 11%;
            width: 55%;
            left: 80px;
            opacity: 0;
            -webkit-animation:shang4 0.5s linear 3.2s 1 forwards;
        }
        .page .page01 .slideUp{
            color: #000;
            font-size: 40px;
            position: absolute;
            bottom: 0%;
            left: 47%;
            -webkit-animation:shang 2s linear 0s infinite forwards;
        }
    </style>
</head>
<body>
<div class="page-wrap">
    <div class="page" style="-webkit-overflow-scrolling: touch;">
        <div class="page01" >
            <img class="logo" src="../resource/img/share/logo.png">
            <img class="top" src="../resource/img/share/top.png">
            <img class="middle" src="../resource/img/share/middle.png">
            <img class="end" src="../resource/img/share/end.png">
            <div class="slideUp">
                <img src="../resource/img/share/arrow.png">
            </div>
        </div>
    </div>
    <div class="page" style="-webkit-overflow-scrolling: touch;">
        <div class="page__inner" style="position: relative; ">
            <div class="page02" >
                <img class="send" src="../resource/img/share/send.png">
                <div class="control">
                    <!-- 循环list -->
                    <c:forEach items="${eventList}" var="dataEvent">

                        <div class="content">
                            <div class="title">
                                <c:out value="${dataEvent.type.desc}"/>
                            </div>
                            <div class="xunhuan">
                                <c:set var="number" value="1" ></c:set>
                                <!-- 循环events -->
                                <c:forEach items="${dataEvent.events}" var="event">
                                    <c:if test="${event.state.type != '1'}">
                                        <c:set var="image" value="${event.image}"/>
                                        <div class="pic">
                                            <div class="number">${number}</div><!-- 编号 -->
                                            <c:set var="number" value="${number + 1}"/>
                                            <img class="aty" src="${image.thumbnail}"><!-- 活动图片 -->
                                            <!-- if判断状态 显示不同状态的报名图片-->
                                            <c:choose>
                                                <c:when test="${event.state.type == '2' || event.state.type == '3'}">
                                                    <a href="${serverContext}/event/v1/detail?id=${event.id}"><!-- 参数 -->
                                                        <div class="btn join">
                                                        </div>
                                                    </a>
                                                </c:when>
                                                <c:when test="${event.state.type == '4'}">
                                                    <img class="next" src="../resource/img/share/next.png">
                                                </c:when>
                                                <c:when test="${event.state.type == '5'}">
                                                    <div class="btn return">
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <img class="ending" src="../resource/img/share/ending.png">
            </div>
        </div>
    </div>
</div>
<script src="../resource/slider/zepto_modify.js"></script>
<script src="../resource/slider/PageSlider.js"></script>
<script type="text/javascript">
        var myBtn = document.getElementById( "canjia" );
        var share_link = "http://www.weegotr.com/weegoevent/event/v1/list";
        var share_img = "http://weegotest.b0.upaiyun.com/brands/origin/573559982584c1097a000007.jpeg";
        var share_title = "我在参加Weego全球限量活动，价值百万的福利你值得拥有！";
        var share_desc = "Weego 简单你的旅行";

        myBtn.onclick = function ( ) {
            DS.ready(function () {
                DS.sendBtnName("canjia"); //这里填写你对按钮的命名
            });
        }
        $.ajax({
            url: "${serverContext}/weixin/v1/config",
            type: "GET" ,
            data:{
               url:location.href.split('#')[0]
            },
            success:function(data) {
                if(data.code == '0'){
                    wx.config({
                        debug: true,
                        appId: data.data.appId, // 必填，公众号的唯一标识
                        timestamp: data.data.timestamp, // 必填，生成签名的时间戳
                        nonceStr: data.data.nonceStr, // 必填，生成签名的随机串
                        signature: data.data.signature,// 必填，签名，见附录1
                        jsApiList: [
                           'onMenuShareTimeline',
                            'onMenuShareAppMessage'
                        ]
                    });
                    wx.ready(function () {
                        DS.ready(function () {
                            wx.onMenuShareAppMessage({
                                title: share_title,
                                desc: share_desc,
                                link:share_link,
                                imgUrl: share_img,
                                success: function () {
                                    DS.sendRepost("appMessage");
                                }
                            });
                            wx.onMenuShareTimeline({
                                title: share_title,
                                desc: share_desc,
                                link: share_link,
                                imgUrl: share_img,
                                success: function () {
                                    DS.sendRepost("timeline");

                                    //other code
                                }/*,
                                fail: function (res){
                                },
                                cancel: function (res){
                                },
                                trigger: function (res){
                                }*/
                            }); 
                        });        
                    });
                    wx.error(function(res){
                        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                        //salert("error:"+res);
                    });
                }   
            },
        },"json")
</script>



<script>
    new PageSlider({
        pages: $('.page-wrap .page'),
    });

    $(".next").click(function(){
        alert("活动尚未开始")
    });
    window.onload = function(){
        var join=$(".join");
        for(var i=0;i<join.length;i++){
            join[i].addEventListener('touchstart',function(){},false);
        };
        var rtn=$(".return");
        for(var i=0;i<rtn.length;i++){
            rtn[i].addEventListener('touchstart',function(){
            },false);
        }
    }
</script>
</body>
</html>