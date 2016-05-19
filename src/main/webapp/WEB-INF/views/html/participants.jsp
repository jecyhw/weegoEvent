<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <title>活动报名列表</title>
</head>
<body>
<div class="container">
    <table class="table table-striped table-bordered table-hover table-condensed">
        <caption>活动报名名单列表</caption>
        <thead>
        <tr>
            <th>微信号</th>
            <th>报名的活动名称</th>
            <th>报名时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${participants}" var="participant">
            <tr>
                <td>${participant.weixin}</td>
                <td>${participant.event.name}</td>
                <td>${participant.date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>