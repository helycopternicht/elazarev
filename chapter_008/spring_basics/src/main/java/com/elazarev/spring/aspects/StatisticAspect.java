package com.elazarev.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Aspect for getting stat information about using loggers.
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 08.02.18
 */
@Aspect
@Component("stat")
public class StatisticAspect {
    /**
     * Map to store statistic information.
     */
    private Map<String, Integer> map;

    /**
     * Default constructor.
     */
    public StatisticAspect() {
        this.map = new HashMap<>();
    }

    /**
     * Collect stat information when logEvent calling.
     * @param jp join point to calling method.
     */
    @AfterReturning("execution(public * com.elazarev.spring.EventLogger.logEvent(..))")
    public void collectStatistic(JoinPoint jp) {
        String clazz = jp.getTarget().getClass().getSimpleName();
        if (map.containsKey(clazz)) {
            map.put(clazz, map.get(clazz) + 1);
        } else {
            map.put(clazz, 1);
        }
    }

    /**
     * Shows statistic results.
     */
    public void showResults() {
        System.out.println("Statistic results:");
        System.out.println(map.toString());
    }
}
