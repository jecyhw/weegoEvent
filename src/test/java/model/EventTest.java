package model;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import me.weego.model.City;
import me.weego.model.Event;
import me.weego.model.EventQuery;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by root on 16-5-12.
 */
public class EventTest {
    @Test
    public void test() {
        Event event = new Event();
        event.setName("活动");

        City city = new City();
        city.setId(new ObjectId().toString());
        city.setName("北京");
        event.setCity(city);

        Event.Image image = new Event.Image();
        image.setSignUp("setSignUp.jpg");
        image.setDetail("setDetail.jpg");
        image.setPartner("setPartner.jpg");
        image.setThumbnail("setThumbnail.jpg");
        event.setImage(image);

        event.setOrder("4");

        Event.State state = new Event.State();
        state.setType("2");
        state.setName("报名中");
        event.setState(state);

        Event.Type type = new Event.Type();
        type.setType("2");
        type.setDesc("六月 . 独一无二的超级福利");
        event.setType(type);

        Event.Time time = new Event.Time();
        time.setSignUp("2016-06-02");
        time.setActive("2016-06-02");
        event.setTime(time);

        MongoClient mongoClient = new MongoClient("123.56.65.17");
        MongoCollection<Document> collection = mongoClient.getDatabase("travel1").getCollection("events");

        if (collection.find(eq("_id", new ObjectId("572d2884e0087f2674bf035f"))).first() != null) {
            System.out.println("hello");
        }

        //collection.insertOne(event.modelToDocument());

        List<Document> documents = new ArrayList<Document>();
        //过滤type等于1(未上线)的活动
        documents.add(new Document("$match", new Document("state.type", new Document("$ne", "1"))));

        //按order排序
        documents.add(new Document("$sort", new Document("order", 1)));

        //需要返回的字段
        Document showField = new Document("_id", "$_id")
                .append("order", "$order")
                .append("state", "$state")
                .append("thumbnail_image", "$thumbnail_image");

        //按照分类type进行分组
        Document subGroup = new Document("_id", "$type")
                .append("type", new Document("$first", "$type"))
                .append("desc_type", new Document("$first", "$desc_type"))
                .append("events", new Document("$push", showField));

        Document group = new Document("$group", subGroup);
        documents.add(group);

        documents.add(new Document("$sort", new Document("type", 1)));

        final List<EventQuery> queries = new ArrayList<EventQuery>();
        collection.aggregate(documents).forEach(new Block<Document>() {
            public void apply(final Document doc) {
                queries.add(new EventQuery().documentToModel(doc, EventQuery.class));
            }
        });
        for (EventQuery eventQuery : queries) {
            System.out.println(eventQuery.modelToDocument(true).toJson());
        }
    }
}
