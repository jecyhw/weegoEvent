package me.weego.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import me.weego.model.EventParticipant;
import me.weego.pojo.ResBody;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by root on 16-5-6.
 */
@Repository
public class EventParticipantDao {
    @Resource
    private Mongo mongo;
    protected MongoCollection<Document> collection;

    @PostConstruct
    private void init() {
        this.collection = mongo.getCollection("event_participants");
    }

    public ResBody add(String weixin) {
        FindIterable<Document> iterable = collection.find(eq("weixin", weixin));
        if (iterable.iterator().hasNext()) {
            return ResBody.returnFail(-1, "已经报名");
        }
        EventParticipant eventParticipant = new EventParticipant();
        eventParticipant.setWeixin(weixin);
        eventParticipant.setDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        collection.insertOne(eventParticipant.modelToDocument());
        return ResBody.returnSuccess(null);
    }
}
