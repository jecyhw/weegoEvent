package me.weego.model;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by root on 16-5-4.
 */
public class EventParticipant extends Model{
    private ObjectId id;
    private String weixin;
    private String date;
    private String shareWay;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShareWay() {
        return shareWay;
    }

    public void setShareWay(String shareWay) {
        this.shareWay = shareWay;
    }

    @Override
    protected void document2Model(Document doc) {
        this.id = Model.getObjectId(doc);
        this.weixin = doc.getString("weixin");
        this.date = doc.getString("date");
        this.shareWay = doc.getString("share_way");
    }

    @Override
    public void modelToDocument(Document doc, boolean flag) {
        if (this.id != null && flag) {
            doc.put("_id", this.id);
        }
        if (this.weixin != null) {
            doc.put("weixin", this.weixin);
        }
        if (this.date != null) {
            doc.put("date", this.date);
        }
        if (this.shareWay != null) {
            doc.put("share_way", this.shareWay);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("_id", this.id)
                .append("weixin", this.weixin)
                .append("date", this.date)
                .append("share_way", this.shareWay)
                .toString();
    }
}
