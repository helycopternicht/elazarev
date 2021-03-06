package com.elazarev.spring.loggers;

import com.elazarev.spring.events.Event;
import org.springframework.stereotype.Component;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
@Component
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event e) {
        System.out.println(e.toString());
    }
}
