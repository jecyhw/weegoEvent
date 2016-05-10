<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ page isELIgnored="false" %>--%>
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
                                            <div class="number">${number}</div><!-- 编号 -->
                                            <c:set var="number" value="${number + 1}"/>
                                            <img class="aty" src="${image.thumbnail}"><!-- 活动图片 -->
                                            <!-- if判断状态 显示不同状态的报名图片-->
                                            <c:choose>
                                                <c:when test="${event.state.type == '2' || event.state.type == '3'}">
                                                    <a href="detail?id=${event.id}"><!-- 参数 -->
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
<script>
    new PageSlider({
        pages: $('.page-wrap .page'),
        rememberLastVisited: true,
        oninit: function(){
            //返回时，需告诉我们此时为返回动作而不是刷新，可以通过 hash 告诉我们
            //PageSlider 所有回调接口 this 指向 PageSlider，方便进行操作
            if(this.lastVisitedIndex){
                this.moveTo(this.lastVisitedIndex, true);
            }
        }
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