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
	<title>活动分享页面</title>
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
								<img class="title_p" src="../resource/img/share/title_m.png">
									<div class="text">
                                        <c:out value="${dataEvent.type.desc}"/>
									</div>	<!-- title -->
								</div>
								<!-- 循环content -->
								<c:forEach items="${dataEvent.events}" var="event">
                                    <c:set var="image" value="${event.image}"/>
									<div class="pic">
										<div class="number">${event.order}</div><!-- 编号 -->
										<img class="circle" src="../resource/img/share/circle.png">
										<img class="aty" src="${image.thumbnail}"><!-- 活动图片 -->
										<!-- if判断状态 显示不同状态的报名图片-->
                                        <c:choose>
                                            <c:when test="${event.state.name=='报名中'}">
                                                <img class="btn" src="../resource/img/share/join.png">
                                                <a class="button clearfix" href="share_detail.jsp?id=${event.id}&detail=${image.detail}&sign_up=${image.signUp}&partner=${image.partner}"><!-- 参数 -->
                                                </a>
                                            </c:when>
                                        </c:choose>
									</div>
								</c:forEach>
							</div>	
						</c:forEach>
					</div>
					<img class="ending" src="../resource/img/share/ending.png">
					<img class="ending1" src="../resource/img/share/logo.png">
		    	</div>
	    	</div>
	    </div>
	</div>

	<script src="../resource/slider/zepto_modify.js"></script>
	<script src="../resource/slider/PageSlider.js"></script>
	<script>
	    new PageSlider({
	        pages: $('.page-wrap .page')
	    });
	</script>

</body>
</html>