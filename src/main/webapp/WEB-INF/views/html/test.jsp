<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="serverContext" value="${pageContext.request.contextPath}" ></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<script id="DS_PRE_JS" type="text/javascript"  src="http://cdn.datastory.com.cn/js/pre-ds-min.js?dsTid=b59d5cd1-2f48-4c76-8868-4f27ab46cb11">
    </script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="../resource/jquery.min.js"></script>
	<title>微信分享测试</title>
</head>
<body>
	<button class="weixin" id="weixin">微信分享</button>


	<script type="text/javascript">
    var myBtn = document.getElementById( "weixin" );
    var share_link = "http://www.weegotr.com/weegoevent/event/v1/list";
    var share_img = "../resource/img/share/cover.jpg";

    myBtn.onclick = function ( ) {
        DS.ready(function () {
            DS.sendBtnName("微信分享"); //这里填写你对按钮的命名
        });
    }
//    }
//    DS.ready(function(){
//        DS.sendAuthUserInfo(userInfo, 'wx15f7fb74b6f16e17');
//    });

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
                        alert("wx.ready");
                        wx.onMenuShareAppMessage({
                            title: share_title,
                            desc: share_desc,
                            link: DS.linkChange(share_link),
                            imgUrl: share_img,
                            success: function () {
                                DS.sendRepost("appMessage");
                                alert("分享成功");
                                //other code
                            }
                        });
                        wx.onMenuShareTimeline({
                            title: share_desc,
                            desc: share_desc,
                            link: DS.linkChange(share_link),
                            imgUrl: share_img,
                            success: function () {
                                DS.sendRepost("timeline");
                                alert("分享成功");
                                //other code
                            }
                        });
                    });
                });
            }   
        },
    },"json")


</script>
	
</body>
</html>