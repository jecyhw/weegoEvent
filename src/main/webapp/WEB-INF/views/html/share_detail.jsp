<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
	<link rel="stylesheet" type="text/css" href="../css/share_detail.css">
	<script type="text/javascript" src="../resource/jquery-1.3.2.min.js"></script>
	<title>活动详情</title>
</head>
<body>	
	<img class="top" src="../resource/img/share/Dior.jpg"><!-- 顶图 -->
	<div class="content">
		<img class="detail" src="../resource/img/share/detail.png"><!-- 报名图 -->
		<div class="text">
			<img src="../resource/img/share/sign.png">
			<div class="name">
				<form action="nameWei" method="post">
					<input class="nameWei" type="text" name="name"/></p>
					<img class="btn" src="../resource/img/share/join.png">
				</form>
		
			</div>
		</div>
		<c:if test="${image != null && image != ''}"> <!-- 判断图片不为空 -->
		<img class="partner" src="../resource/img/share/partner.png"><!-- 合作伙伴图 -->
		</c:if>
		<img class="ending" src="../resource/img/share/ending_d.png">
	</div>
	<!-- <script type="text/javascript">
		$(document).ready(function(){
		  $("form").submit(function(e){
		    alert("Submitted");
		  });
		  $("button").click(function(){
		    $("form").submit();
		  });  
		});
		$(document).ready(function(){
			
		})
	</script> -->
</body>
</html>