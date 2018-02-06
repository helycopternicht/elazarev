package com.elazarev.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Map;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
public class App {

    private Client client;

    private EventLogger defaultEventLogger;

    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultEventLogger = eventLogger;
        this.loggers = loggers;
    }

    public App() {
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
        EventLogger logger = loggers.get(e.getType());
        if (logger == null) {
            logger = defaultEventLogger;
        }
        logger.logEvent(e);
    }
}
