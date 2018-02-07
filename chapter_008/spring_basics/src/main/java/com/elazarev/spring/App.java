package com.elazarev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
@Component
public class App {

    private EventLogger infoLogger;
    private EventLogger errorLogger;


    @Autowired
    public App(@Qualifier("consoleEventLogger") EventLogger info, @Qualifier("cacheFileEventLogger") EventLogger error) {
        this.infoLogger = info;
        this.errorLogger = error;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = ctx.getBean("app", App.class);
        app.logEvent(ctx.getBean("event", Event.class));
        app.logEvent(ctx.getBean("event", Event.class));
        app.logEvent(ctx.getBean("event", Event.class));
        app.logEvent(ctx.getBean("event", Event.class));
        ctx.close();
    }

    public void logEvent(Event e) {
        if (e.getType() == EventType.ERROR) {
            errorLogger.logEvent(e);
        } else {
            infoLogger.logEvent(e);
        }
    }
}
