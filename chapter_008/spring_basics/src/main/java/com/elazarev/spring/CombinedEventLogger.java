package com.elazarev.spring;

import java.util.Collection;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> loggers;

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
