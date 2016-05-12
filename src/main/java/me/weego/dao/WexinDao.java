package me.weego.dao;

import com.mongodb.client.MongoCollection;
import me.weego.model.Wexin;
import me.weego.util.WexinUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by root on 16-5-9.
 */
@Repository
public class WexinDao {
    @Resource
    private Mongo mongo;
    private MongoCollection<Document> collection;
    private Wexin wexin;

    @PostConstruct
    private void init() {
        this.collection = mongo.getCollection("weixin");
        wexin = new Wexin();
    }

    public Wexin.AccessToken getAccessToken() {
        getJsapiTicket();
        return wexin.getAccessToken();
    }

    public Wexin.JsapiTicket getJsapiTicket() {
        Document doc = this.collection.find().first();

        if (doc == null) {//第一次请求
            wexin.setAccessToken(WexinUtil.getAccessToken());
            wexin.setJsapiTicket(WexinUtil.getJsapiTicket(wexin.getAccessToken().getAccessToken()));
            this.collection.insertOne(wexin.modelToDocument());
        } else {
            wexin.documentToModel(doc);
            boolean update = false;
            //判断access_token是否失效
            Wexin.AccessToken accessToken = wexin.getAccessToken();
            if (DateUtils.addSeconds(accessToken.getDate(), WexinUtil.getExpire(accessToken.getExpire())).before(new Date())) {
                //access_token已经失效,需要重新获取
                accessToken = WexinUtil.getAccessToken();
                update = true;
            }
            //判断jsapi_ticket是否失效
            Wexin.JsapiTicket jsapiTicket = wexin.getJsapiTicket();
            if (DateUtils.addSeconds(jsapiTicket.getDate(), WexinUtil.getExpire(jsapiTicket.getExpire())).before(new Date())) {
                //jsapi_ticket已经失效,需要重新获取
                jsapiTicket = WexinUtil.getJsapiTicket(accessToken.getAccessToken());
                update = true;
            }
            if (update) {
                wexin.setAccessToken(accessToken);
                wexin.setJsapiTicket(jsapiTicket);
                this.collection.findOneAndReplace(new Document(), wexin.modelToDocument());
            }
        }
        return wexin.getJsapiTicket();
    }

    public Wexin.Config getConfig(String url) {
        Wexin.Config config = new Wexin.Config();
        config.setAppId(WexinUtil.getAppId());
        config.setTimestamp(System.currentTimeMillis());
        config.setNonceStr(RandomStringUtils.randomAlphanumeric(32));
        config.encryptionBySha1(getJsapiTicket().getJsapiTicket(), url);
        return config;
    }
}
