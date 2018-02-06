package com.elazarev.spring;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
public class Event {

    private int id;

    private String msg;

    private Date date;

    private DateFormat df;

    private EventType type;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append("id=").append(id);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", date=").append(df.format(date));
        sb.append('}');
        return sb.toString();
    }
}
