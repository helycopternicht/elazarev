package com.elazarev.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
@Component
public class Client {

    private int id;

    private String fullName;

    private String greeting;

    public Client(@Value("1") int id, @Value("Joe Dow") String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }
    @Value("Hello there!")
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
