package me.weego.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * @author tcl
 */
public class Mongo {
    private String server;
    private String db;

    private MongoClient mongoClient;

    private void init() {
        this.mongoClient = new MongoClient(server);
    }

    private void destroy() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public MongoCollection<Document> getCollection(String collection) {
        return mongoClient.getDatabase(db).getCollection(collection);
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

}
