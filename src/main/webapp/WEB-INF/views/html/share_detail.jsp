<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="serverContext" value="${pageContext.request.contextPath}" ></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
    <link rel="stylesheet" type="text/css" href="../css/share_detail.css">
    <script id="DS_PRE_JS" type="text/javascript"  src="http://cdn.datastory.com.cn/js/pre-ds-min.js?dsTid=b59d5cd1-2f48-4c76-8868-4f27ab46cb11">
    </script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="../resource/jquery.min.js"></script>
    <title>${event.name}</title>
</head>
<body>
<img class="top" src="${event.image.detail}"><!-- 顶图 -->
<div class="content">
    <img class="detail" src="${event.image.signUp}"><!-- 报名图 -->
    <div class="text">
        <img src="../resource/img/share/sign.png">
        <div class="name">
            <form action="${serverContext}/event/v1/join" method="post">
                <input class="nameWei" type="text" name="weixin"/>
                <input type="hidden" value="${event.id}" name="id"/>
                <input type="hidden" value="${event.state.type}" name="type"/>
                <button class="canjia" id="canjia" name="canjia"></button>
            </form>
        </div>
    </div>
    <c:if test="${event.image.partner != null && event.image.partner != ''}">
        <img class="partner" src="${event.image.partner}"><!-- 合作伙伴图 -->
    </c:if>
    <img class="ending" src="../resource/img/share/ending_d.png">
    <img class="share"  src="../resource/img/share/share.png">
</div>

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
                $.post("${serverContext}/event/v1/join", {
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