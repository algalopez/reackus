package com.algalopez.reackus.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@ApplicationScoped
@Slf4j
public class AuditableAspect {

    @Before("execution(public * com.algalopez.reackus..*Actor*.*(..))")
    public void logServiceCall(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String arguments = Stream.of(args).map(Object::toString).collect(Collectors.joining(","));
        String signatureName = joinPoint.getSignature().getName();

        log.info("{}({})", signatureName, arguments);
    }
}
