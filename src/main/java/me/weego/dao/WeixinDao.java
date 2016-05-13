package me.weego.dao;

import com.mongodb.client.MongoCollection;
import me.weego.model.Weixin;
import me.weego.util.WeixinUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by root on 16-5-9.
 */
@Repository
public class WeixinDao {
    @Resource
    private Mongo mongo;
    private MongoCollection<Document> collection;
    private Weixin weixin;

    @PostConstruct
    private void init() {
        this.collection = mongo.getCollection("weixin");
        weixin = new Weixin();
    }

    public Weixin.AccessToken accessToken() {
        this.jsapiTicket();
        return weixin.getAccessToken();
    }

    public Weixin.JsapiTicket jsapiTicket() {
        Document doc = this.collection.find().first();

        if (doc == null) {//第一次请求
            weixin.setAccessToken(WeixinUtil.getAccessToken());
            weixin.setJsapiTicket(WeixinUtil.getJsapiTicket(weixin.getAccessToken().getAccessToken()));
            this.collection.insertOne(weixin.modelToDocument());
        } else {
            weixin.documentToModel(doc);
            boolean update = false;
            //判断access_token是否失效
            Weixin.AccessToken accessToken = weixin.getAccessToken();
            if (DateUtils.addSeconds(accessToken.getDate(), WeixinUtil.getExpire(accessToken.getExpire())).before(new Date())) {
                //access_token已经失效,需要重新获取
                accessToken = WeixinUtil.getAccessToken();
                update = true;
            }
            //判断jsapi_ticket是否失效
            Weixin.JsapiTicket jsapiTicket = weixin.getJsapiTicket();
            if (DateUtils.addSeconds(jsapiTicket.getDate(), WeixinUtil.getExpire(jsapiTicket.getExpire())).before(new Date())) {
                //jsapi_ticket已经失效,需要重新获取
                jsapiTicket = WeixinUtil.getJsapiTicket(accessToken.getAccessToken());
                update = true;
            }
            if (update) {
                weixin.setAccessToken(accessToken);
                weixin.setJsapiTicket(jsapiTicket);
                this.collection.findOneAndReplace(new Document(), weixin.modelToDocument());
            }
        }
        return weixin.getJsapiTicket();
    }

    public Weixin.Config config(String url) {
        Weixin.Config config = new Weixin.Config();
        config.setAppId(WeixinUtil.getAppId());
        config.setTimestamp(System.currentTimeMillis());
        config.setNonceStr(RandomStringUtils.randomAlphanumeric(32));
        config.encryptionBySha1(this.jsapiTicket().getJsapiTicket(), url);
        return config;
    }
}
