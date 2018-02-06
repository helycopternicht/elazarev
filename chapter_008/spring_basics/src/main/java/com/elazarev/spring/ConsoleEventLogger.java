package com.elazarev.spring;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 06.02.18
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event e) {
        System.out.println(e.toString());
    }
}
