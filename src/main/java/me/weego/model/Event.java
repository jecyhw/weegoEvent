package me.weego.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by ln on 16-4-22.
 * 城市活动
 */
public class Event extends Model{
    private ObjectId id;
    private City city;
    private String name;
    private State state;
    private Type type;
    private String order;
    private Time time;
    private Image image;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    protected void document2Model(Document doc) {
        this.id = Model.getObjectId(doc);
        this.city = new City().documentToModel(Model.getSubDocument(doc, "city"), City.class);
        this.name = doc.getString("name");
        this.state = new State().documentToModel(Model.getSubDocument(doc, "state"), State.class);
        this.type = new Type().documentToModel(doc, Type.class);
        this.order = doc.getString("order");
        this.time = new Time().documentToModel(Model.getSubDocument(doc, "time"), Time.class);
        this.image = new Image().documentToModel(doc, Image.class);
    }

    @Override
    public void modelToDocument(Document doc, boolean flag) {
        if (flag) {
            doc.put("_id", this.id.toString());
        }
        if (this.city != null) {
            doc.put("city", this.city.modelToDocument(true));
        }
        if (this.name != null) {
            doc.put("name", this.name);
        }
        if (this.state != null) {
            doc.put("state", this.state.modelToDocument());
        }
        if (this.type != null) {
            doc.put("type", this.type.modelToDocument());
            this.type.modelToDocument(doc);
        }
        if (this.order != null) {
            doc.put("order", this.order);
        }
        if (this.time != null) {
            doc.put("time", this.time.modelToDocument());
        }
        if (this.image != null) {
            doc.put("image", this.image.modelToDocument());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("_id", this.id)
                .append("city", this.city)
                .append("name", this.name)
                .append("state", this.state)
                .append("type", this.type)
                .append("order", this.order)
                .append("time", this.time)
                .append("image", this.image)
                .toString();
    }

    static public class State extends Model{
        private String type;
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected void document2Model(Document doc) {
            this.name = doc.getString("name");
            this.type = doc.getString("type");
        }

        @Override
        public void modelToDocument(Document doc, boolean flag) {
            if (this.type != null) {
                doc.put("type", this.type);
            }
            if (this.name != null) {
                doc.put("name", this.name);
            }
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("type", this.type)
                    .append("name", this.name)
                    .toString();
        }
    }

    static public class Type extends Model{
        private String type;
        private String desc;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        protected void document2Model(Document doc) {
            this.type = doc.getString("type");
            this.desc = doc.getString("desc_type");
        }

        @Override
        public void modelToDocument(Document doc, boolean flag) {
            if (this.type != null) {
                doc.put("type", this.type);
            }
            if (this.desc != null) {
                doc.put("desc", this.desc);
            }
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("type", this.type)
                    .append("desc", this.desc)
                    .toString();
        }
    }

    static public class Time extends Model{
        private String signUp;
        private String active;

        public String getSignUp() {
            return signUp;
        }

        public void setSignUp(String signUp) {
            this.signUp = signUp;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        @Override
        protected void document2Model(Document doc) {
            this.setActive(doc.getString("active"));
            this.setSignUp(doc.getString("sign_up"));
        }

        @Override
        public void modelToDocument(Document doc, boolean flag) {
            doc.put("active", this.active);
            doc.put("sign_up", this.signUp);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("active", this.active)
                    .append("sign_up", this.signUp)
                    .toString();
        }
    }

    static public class Image extends Model{
        private String thumbnail;
        private String detail;
        private String signUp;
        private String partner;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getSignUp() {
            return signUp;
        }

        public void setSignUp(String signUp) {
            this.signUp = signUp;
        }

        public String getPartner() {
            return partner;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }


        @Override
        protected void document2Model(Document doc) {
            this.thumbnail = addImageUrlPrefix(doc.getString(addImageSuffix("thumbnail")));
            this.detail = addImageUrlPrefix(doc.getString(addImageSuffix("detail")));
            this.signUp = addImageUrlPrefix(doc.getString(addImageSuffix("sign_up")));
            this.partner = addImageUrlPrefix(doc.getString(addImageSuffix("partner")));
        }

        @Override
        public void modelToDocument(Document doc, boolean flag) {
            if (this.thumbnail != null) {
                doc.put("thumbnail", removeImageUrlPrefix(this.thumbnail));
            }
            if (this.detail != null) {
                doc.put("detail", removeImageUrlPrefix(this.detail));
            }
            if (this.signUp != null) {
                doc.put("sign_up", removeImageUrlPrefix(this.signUp));
            }
            if (this.partner != null) {
                doc.put("partner", removeImageUrlPrefix(this.partner));
            }
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("thumbnail", this.thumbnail)
                    .append("detail", this.detail)
                    .append("sign_up", this.signUp)
                    .append("partner", this.partner)
                    .toString();
        }

        static final String urlPrefix = "http://weegotest.b0.upaiyun.com/brands/origin/";

        private String addImageUrlPrefix(String image) {
            return urlPrefix + image;
        }

        private String removeImageUrlPrefix(String image) {
            int index = image.indexOf(urlPrefix);
            if (index < 0) {
                return image;
            }
            return image.substring(index);
        }

        static final String suffix = "_image";
        private String addImageSuffix(String image) {
            return image + suffix;
        }
    }
}
