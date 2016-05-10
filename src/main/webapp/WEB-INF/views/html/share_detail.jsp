<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
    <link rel="stylesheet" type="text/css" href="../css/share_detail.css">
    <script type="text/javascript" src="../resource/jquery-1.3.2.min.js"></script>
    <title>${event.name}</title>
</head>
<body>
<img class="top" src="${event.image.detail}"><!-- 顶图 -->
<div class="content">
    <img class="detail" src="${event.image.signUp}"><!-- 报名图 -->
    <div class="text">
        <img src="../resource/img/share/sign.png">
        <div class="name">
            <form action="join" method="post">
                <input class="nameWei" type="text" name="weixin"/>
                <input type="hidden" value="${event.id}" name="id"/>
                <input type="hidden" value="${event.state.type}" name="type"/>
                <div class="canjia"></div>
            </form>
        </div>
    </div>
    <c:if test="${event.image.partner != null && event.image.partner != ''}">
        <img class="partner" src="${event.image.partner}"><!-- 合作伙伴图 -->
    </c:if>
    <img class="ending" src="../resource/img/share/ending_d.png">
    <img class="share"  src="../resource/img/share/share.png">
</div>
<script id="DS_PRE_JS" type="text/javascript"
        src="http://cdn.datastory.com.cn/js/pre-ds-min.js?dsTid=b59d5cd1-2f48-4c76-8868-4f27ab46cb11">
</script>
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
        $(".share").click(function (){
            $(".share").removeClass("show");
        })
        $(".canjia").click(function () {
            if ($("input[name='type']").val() != '2') {
                alert("报名已经截止");
            } else {
                $.post("join", {
                    id: $("input[name='id']").val(),
                    weixin: $("input[name='weixin']").val()
                }, function (data) {
                   if (data.code == '0') {
                       $(".share").addClass("show");
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