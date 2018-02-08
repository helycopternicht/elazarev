package com.elazarev.spring.loggers;

import com.elazarev.spring.events.Event;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
public interface EventLogger {

    void logEvent(Event e);
}
