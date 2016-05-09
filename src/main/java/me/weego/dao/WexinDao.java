package me.weego.dao;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by root on 16-5-9.
 */
public class WexinDao {
    @Resource
    private Mongo mongo;
    private MongoCollection<Document> collection;

    @PostConstruct
    private void init() {
        this.collection = mongo.getCollection("weixin");
    }

    public String getAccessToken() {
        Document doc = this.collection.find().first();
        if (doc == null) {

        }
        return null;
    }

    public String getJsapiTicket() {
        return null;
    }
}
