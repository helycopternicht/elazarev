package com.elazarev.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
@Component
@Scope("prototype")
public class Event {

    private int id;

    private String msg;

    private LocalDateTime date;

    private EventType type;

    public Event(@Value("#{T(java.time.LocalDateTime).now()}") LocalDateTime date) {
        this.date = date;
    }

    public EventType getType() {
        return type;
    }

    @Value("#{T(com.elazarev.spring.EventType).ERROR}")
    public void setType(EventType type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    @Value("Some event for ....")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Value("#{T(java.lang.Math).random() * 10}")
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append(" id='").append(id);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", type='").append(type);
        sb.append(", date=").append(date.format(DateTimeFormatter.ISO_DATE));
        sb.append('}');
        return sb.toString();
    }
}
