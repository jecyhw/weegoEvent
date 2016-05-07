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
<img class="top" src="${detail}"><!-- 顶图 -->
<div class="content">
    <img class="detail" src="${sign_up}"><!-- 报名图 -->
    <div class="text">
        <img src="../resource/img/share/sign.png">
        <div class="name">
            <form action="/event/join" method="post">
                <input class="nameWei" type="text" name="weixin"/>
                <input type="hidden" value="${id}" name="id"/>
                <input type="hidden" value="${type}" name="type"/>
                <button class="canjia" type="submit"></button>
            </form>
        </div>
    </div>
    <c:if test="${partner != null && partner != ''}">
        <img class="partner" src="${partner}"><!-- 合作伙伴图 -->
    </c:if>
    <img class="ending" src="../resource/img/share/ending_d.png">
</div>
<script>
    window.onload = function(){
        var doma=$(".canjia");
        for(var i=0;i<doma.length;i++){
            doma[i].addEventListener('touchstart',function(){},false);
        };
    }
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $("button[type='submit']").click(function () {
            if ($("input[name='type']").val() != '2') {
                alert("报名已经截止");
            } else {
                $.post("/event/join", {
                    id: $("input[name='id']").val(),
                    weixin: $("input[name='weixin']").val()
                }, function (data) {
                   if (data.code == '0') {
                       alert("报名成功");
                   } else {
                       alert(data.msg);
                   }
                }, "json");
            }
            return false;
        });
    });
</script>
</body>
</html>