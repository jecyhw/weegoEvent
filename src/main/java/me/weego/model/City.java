package me.weego.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.Document;

/**
 * Created by root on 16-5-4.
 */
public class City extends Model{
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void parse(Document doc) {

    }

    @Override
    protected void document2Model(Document doc) {
        this.id = Model.getObjectId(doc).toString();
        this.name = doc.getString("name");
    }

    @Override
    public void modelToDocument(Document doc, boolean flag) {
        if (this.id != null && flag) {
            doc.put("_id", this.id);
        }
        if (this.name != null) {
            doc.put("name", this.name);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("_id", this.id)
                .append("name", this.name)
                .toString();
    }
}
