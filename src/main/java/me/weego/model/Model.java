package me.weego.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;

/**
 * Created by root on 16-5-5.
 */
abstract public class Model {

    /**
     * mongodb查询的document转对应的java model
     * @param doc
     */
    abstract protected void document2Model(Document doc);

    /**
     * java model转mongodb的document
     * @param doc
     * @param flag flag标志是否要将id也添加到document中(对于自动生成的id不需要,但对与引用的id需要)
     */
    abstract public void modelToDocument(Document doc, boolean flag);

    /**
     * mongodb的document转model
     * @param doc
     * @return 并返回对应的model
     */
    public Model documentToModel(Document doc) {
        if (doc != null) {
            this.document2Model(doc);
            return this;
        }
        return null;
    }

    public <T extends Model> T documentToModel(Document doc, Class<T> clazz) {
        if (doc != null) {
            this.document2Model(doc);
            return (T) this;
        }
        return null;
    }

    public void modelToDocument(Document doc) {
        modelToDocument(doc, false);
    }

    /**
     * model转mongodb的document
     * @return 不把id添加到Document中
     */
    public Document modelToDocument() {
        Document doc = new Document();
        this.modelToDocument(doc);
        return doc;
    }

    /**
     * model转mongodb的document
     * @param flag 表示是否要将id添加到Document中
     * @return 并返回对应的model
     */
    public Document modelToDocument(boolean flag) {
        Document doc = new Document();
        this.modelToDocument(doc, flag);
        return doc;
    }

    static public Document getSubDocument(Document source, String key) {
        return source.get(key, Document.class);
    }

    static public ObjectId getObjectId(Document doc) {
        try {
            return doc.getObjectId("_id");
        } catch (Exception e) {
            return  new ObjectId(doc.getString("_id"));
        }
    }
}
