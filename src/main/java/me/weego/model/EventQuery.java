package me.weego.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-5-6.
 */
public class EventQuery extends Model{
    Event.Type type;
    List<Event> events;

    public Event.Type getType() {
        return type;
    }

    public void setType(Event.Type type) {
        this.type = type;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    protected void document2Model(Document doc) {
        ArrayList tempDocs = doc.get("events", ArrayList.class);
        if (tempDocs != null) {
            this.events = new ArrayList<Event>();
            for (Object tempDoc : tempDocs) {
                this.events.add(new Event().documentToModel((Document) tempDoc, Event.class));
            }
        }
        this.type = new Event.Type().documentToModel(doc, Event.Type.class);
    }

    @Override
    public void modelToDocument(Document doc, boolean flag) {
        if (this.type != null) {
            doc.put("type", this.type.modelToDocument());
        }
        if (this.events != null) {
            List<Document> tempEvents = new ArrayList<Document>();
            for (Event event : events) {
                tempEvents.add(event.modelToDocument(true));
            }
            doc.put("events", tempEvents);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", this.type)
                .append("events", this.events)
                .toString();
    }
}
