package com.elazarev.spring.loggers;

import com.elazarev.spring.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
@Component("CombinedEventLogger")
public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> loggers;

    @Autowired
    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event e) {
        for (EventLogger logger : this.loggers) {
            logger.logEvent(e);
        }
    }
}
