package com.elazarev.spring;

import com.elazarev.spring.aspects.StatisticAspect;
import com.elazarev.spring.events.Event;
import com.elazarev.spring.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Main application class.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
@Component
public class App {
    /**
     * Logger for info events.
     */
    private EventLogger infoLogger;
    /**
     * Logger for error events.
     */
    private EventLogger errorLogger;
    /**
     * Even odd helper.
     */
    private boolean error;

    /**
     * Default constructor.
     * @param info info logger.
     * @param error error logger.
     */
    @Autowired
    public App(@Qualifier("consoleEventLogger") EventLogger info, @Qualifier("cacheFileEventLogger") EventLogger error) {
        this.infoLogger = info;
        this.errorLogger = error;
    }

    /**
     * Main method.
     * @param args app args.
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = ctx.getBean("app", App.class);
        app.logEvent(ctx.getBean("event", Event.class));
        app.logEvent(ctx.getBean("event", Event.class));
        app.logEvent(ctx.getBean("event", Event.class));
        app.logEvent(ctx.getBean("event", Event.class));

        StatisticAspect stat = ctx.getBean("stat", StatisticAspect.class);
        stat.showResults();
        ctx.close();
    }

    /**
     * Logs event.
     * @param e event.
     */
    public void logEvent(Event e) {
        if (error) {
            errorLogger.logEvent(e);
        } else {
            infoLogger.logEvent(e);
        }
        error = !error;
    }
}
