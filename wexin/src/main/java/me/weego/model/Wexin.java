package me.weego.model;

import org.bson.Document;

import java.util.Date;

/**
 * Created by root on 16-5-9.
 */
public class Wexin extends Model{
    final public static Integer ACCESS_TOKEN_EXPRIE;
    final public static Integer JSAPI_TICKET_EXPRIE;

    static {
        int temp = 3600;
        try {
            temp = Integer.valueOf(System.getProperty("access_token_expire"));
        } catch (Exception e) {
        }
        ACCESS_TOKEN_EXPRIE = temp;

        temp = 3600;
        try {
            temp  = Integer.valueOf(System.getProperty("jsapi_ticket_expire"));
        } catch (Exception e) {
        }
        JSAPI_TICKET_EXPRIE = temp;
    }


    private AccessToken accessToken = new AccessToken();
    private JsapiTicket jsapiTicket = new JsapiTicket();

    public static Integer getAccessTokenExprie() {
        return ACCESS_TOKEN_EXPRIE;
    }

    public static Integer getJsapiTicketExprie() {
        return JSAPI_TICKET_EXPRIE;
    }

    @Override
    protected void document2Model(Document doc) {
        accessToken.documentToModel(doc.get("access_token", Document.class));
        jsapiTicket.document2Model(doc.get("jsapi_ticket", Document.class));
    }

    @Override
    public void modelToDocument(Document doc, boolean flag) {
        doc.append("access_token", accessToken.modelToDocument());
        doc.append("jsapi_ticket", jsapiTicket.modelToDocument());
    }

    private class AccessToken  extends Model{
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
    }

    private class JsapiTicket  extends Model{
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
    }
}
