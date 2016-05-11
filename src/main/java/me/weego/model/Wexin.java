package me.weego.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.Document;

import java.util.Date;

/**
 * Created by root on 16-5-9.
 */
public class Wexin extends Model{

    private AccessToken accessToken;
    private JsapiTicket jsapiTicket;

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public JsapiTicket getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(JsapiTicket jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    @Override
    protected void document2Model(Document doc) {
        accessToken = new AccessToken().documentToModel(doc.get("access_token", Document.class), AccessToken.class);
        jsapiTicket = new JsapiTicket().documentToModel(doc.get("jsapi_ticket", Document.class), JsapiTicket.class);
    }

    @Override
    public void modelToDocument(Document doc, boolean flag) {
        if (this.accessToken != null) {
            doc.append("access_token", this.accessToken.modelToDocument());
        }
        if (this.jsapiTicket != null) {
            doc.append("jsapi_ticket", this.jsapiTicket.modelToDocument());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accessToken", this.accessToken)
                .append("jsapiTicket", this.jsapiTicket)
                .toString();
    }

    static public class AccessToken  extends Model{
        private String accessToken;
        private Date date;
        private Integer expire;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Integer getExpire() {
            return expire;
        }

        public void setExpire(Integer expire) {
            this.expire = expire;
        }

        @Override
        protected void document2Model(Document doc) {
            this.accessToken = doc.getString("access_token");
            this.date = doc.getDate("date");
            this.expire = doc.getInteger("expire");
        }

        @Override
        public void modelToDocument(Document doc, boolean flag) {
            doc.append("access_token", this.accessToken);
            doc.append("date", this.date);
            doc.append("expire", this.expire);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("accessToken", this.accessToken)
                    .append("date", this.date)
                    .append("expire", this.expire)
                    .toString();
        }
    }

    static public class JsapiTicket  extends Model{
        private String jsapiTicket;
        private Date date;
        private Integer expire;

        public String getJsapiTicket() {
            return jsapiTicket;
        }

        public void setJsapiTicket(String jsapiTicket) {
            this.jsapiTicket = jsapiTicket;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Integer getExpire() {
            return expire;
        }

        public void setExpire(Integer expire) {
            this.expire = expire;
        }

        @Override
        protected void document2Model(Document doc) {
            this.jsapiTicket = doc.getString("jsapi_ticket");
            this.date = doc.getDate("date");
            this.expire = doc.getInteger("expire");
        }

        @Override
        public void modelToDocument(Document doc, boolean flag) {
            doc.append("jsapi_ticket", this.jsapiTicket);
            doc.append("date", this.date);
            doc.append("expire", this.expire);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("jsapiTicket", this.jsapiTicket)
                    .append("date", this.date)
                    .append("expire", this.expire)
                    .toString();
        }
    }
}
