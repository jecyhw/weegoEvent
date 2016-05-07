<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<img class="top" src="<%=request.getParameter("detail")%>"><!-- 顶图 -->
<div class="content">
    <img class="detail" src="<%=request.getParameter("sign_up")%>"><!-- 报名图 -->
    <div class="text">
        <img src="../resource/img/share/sign.png">
        <div class="name">
            <form action="nameWei" method="post">
                <input class="nameWei" type="text" name="name"/>
                <input type="hidden" value="<%=request.getParameter("id")%>" name="id"/>
                <button class="canjia" type="submit"></button>
            </form>
        </div>
    </div>
    <%
        String partner = request.getParameter("partner");
        if (partner != null) {// 判断图片不为空
    %>
    <img class="partner" src="<%=partner%>"><!-- 合作伙伴图 -->
    <%
        }
    %>
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
<!-- <script type="text/javascript">
    $(document).ready(function(){
      $("form").submit(function(e){
        alert("Submitted");
      });
      $("button").click(function(){
        $("form").submit();
      });
    });
</script> -->
</body>
</html>