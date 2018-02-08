package com.elazarev.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 08.02.18
 */
@Component
@Aspect
public class LoggingAspect {
    /**
     * Pointcut to logEvent method.
     */
    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods() {
    }

    /**
     * Advice to logging pointcut.
     * @param jp join point to get class info.
     */
     @Before("allLogEventMethods()")
    public void logBefore(JoinPoint jp) {
         System.out.println(jp.getTarget().getClass().getSimpleName());
    }
}
