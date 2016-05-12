package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import me.weego.model.Event;
import me.weego.model.EventParticipant;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.Date;

/**
 * Created by root on 16-5-12.
 */
public class EventParticipantTest {
    @Test
    public void test() {
        EventParticipant eventParticipant = new EventParticipant();
        eventParticipant.setWeixin("11111");
        eventParticipant.setDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        Event event = new Event();
        event.setId(new ObjectId().toString());
        eventParticipant.setEvent(event);

        MongoClient mongoClient = new MongoClient("123.56.65.17");
        MongoCollection<Document> collection = mongoClient.getDatabase("travel1").getCollection("event_participants");
        collection.insertOne(eventParticipant.modelToDocument());
    }
}
