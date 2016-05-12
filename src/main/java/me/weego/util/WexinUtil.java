package me.weego.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import me.weego.model.Wexin;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * Created by root on 16-5-9.
 */
@Component
public class WexinUtil {
    private static Wechat wechat;

    @Resource
    public void setWechat(Wechat wechat) {//使用注解给static类型赋值
        WexinUtil.wechat = wechat;
    }

    static private String getAccessTokenUrl() {
        return new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?")
                .append("grant_type=").append(wechat.getGrantType())
                .append("&appid=").append(wechat.getAppid())
                .append("&secret=").append(wechat.getSecret())
                .toString();
    }

    static public String getAppId() {
        return wechat.getAppid();
    }

    static private String getJsapiTicketUrl(String accessToken) {
        return new StringBuilder("https://api.weixin.qq.com/cgi-bin/ticket/getticket?")
                .append("access_token=").append(accessToken)
                .append("&type=jsapi")
                .toString();
    }

    static public Wexin.AccessToken getAccessToken() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getAccessTokenUrl())
                .build();

        String result = "";
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                result = response.body().string();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(result);
        Wexin.AccessToken accessToken = new Wexin.AccessToken();
        accessToken.setDate(new Date());
        accessToken.setAccessToken(jsonObject.getString("access_token"));
        accessToken.setExpire(jsonObject.getInt("expires_in"));

        LoggerUtil.logBiz("accessToken", accessToken);

        return accessToken;
    }

    static public Wexin.JsapiTicket getJsapiTicket(String accessToken) {
        LoggerUtil.logBiz("accessToken", accessToken);

        String url = getJsapiTicketUrl(accessToken);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        String result = "";
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                result = response.body().string();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(result);
        Wexin.JsapiTicket jsapiTicket = new Wexin.JsapiTicket();
        jsapiTicket.setDate(new Date());
        jsapiTicket.setJsapiTicket(jsonObject.getString("ticket"));
        jsapiTicket.setExpire(jsonObject.getInt("expires_in"));

        LoggerUtil.logBiz("errmsg", jsonObject.getString("errmsg"));
        LoggerUtil.logBiz("accessToken", jsapiTicket);
        return jsapiTicket;
    }


    /**
     * 获取access_token和jsapi_ticket的过期时间,默认是实际时间减去3600s
     * @param expire 微信jsapi接口获取的实际过期时间
     * @return 返回减去后的时间
     */
    static public Integer getExpire(Integer expire) {
        int wechatExpire = Integer.MAX_VALUE;
        try {
            wechatExpire = Integer.parseInt(wechat.expire);
        } catch (Exception e) {

        }
        if (expire > wechatExpire) {
            return wechatExpire;
        } else {
            return expire / 2;
        }
    }

    static public class Wechat {
        private String appid;
        private String secret;
        private String grantType;
        private String expire;

        private Wechat() {
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getGrantType() {
            return grantType;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }

        public String getExpire() {
            return expire;
        }

        public void setExpire(String expire) {
            this.expire = expire;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("appid", this.appid)
                    .append("secret", this.secret)
                    .append("grantType", this.grantType)
                    .append("expire", this.grantType)
                    .toString();
        }
    }

}
