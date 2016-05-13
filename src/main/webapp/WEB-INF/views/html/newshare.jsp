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
    
    <title>我在参加Weego全球限量活动，价值百万的福利你值得拥有！</title>
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
                                            <div class="number">${number}</div>
                                            <c:set var="number" value="${number + 1}"/>
                                            <img class="aty" src="${image.thumbnail}">
                                            <c:choose>
                                                <c:when test="${event.state.type == '2' || event.state.type == '3'}">
                                                    <a href="${serverContext}/event/v1/detail?id=${event.id}">
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
<script id="DS_PRE_JS" type="text/javascript"  src="http://cdn.datastory.com.cn/js/pre-ds-min.js?dsTid=b59d5cd1-2f48-4c76-8868-4f27ab46cb11">
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../resource/jquery.min.js"></script>

<script src="../resource/slider/zepto_modify.js"></script>
<script src="../resource/slider/PageSlider.js"></script>
<script type="text/javascript">
    var $j = jQuery.noConflict();
    $j(document).ready(function(){
        new PageSlider({
            pages: $('.page-wrap .page'),
        });

        var share_link = "http://www.weegotr.com/weegoevent/event/v1/list";
        var share_img = "http://weegotest.b0.upaiyun.com/brands/origin/573559982584c1097a000007.jpeg";
        var share_title = "我在参加Weego全球限量活动，价值百万的福利你值得拥有！";
        var share_desc = "Weego 简单你的旅行";

        $j.ajax({
            url: "${serverContext}/weixin/v1/config",
            type: "GET" ,
            data:{
               url:location.href.split('#')[0]
            },
            success:function(data) {
                if(data.code == '0'){
                    var con = data.data;
                    wx.config({
                        debug: false,
                        appId: con.appId, 
                        timestamp: con.timestamp, 
                        nonceStr: con.nonceStr,
                        signature: con.signature,
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
                                }
                            }); 
                        });        
                    });
                }   
            },
        },"json")
        
        $(".next").click(function(){
            alert("活动尚未开始")
        });

        var join=$j(".join");
        var rtn=$j(".return");
        for(var i=0;i<join.length;i++){
            join[i].addEventListener('touchstart',function(){},false);
        };
        for(var i=0;i<rtn.length;i++){
            rtn[i].addEventListener('touchstart',function(){},false);
        }
        
    });       
</script>
</body>
</html>