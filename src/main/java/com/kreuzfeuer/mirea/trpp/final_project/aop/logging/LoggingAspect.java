package com.kreuzfeuer.mirea.trpp.final_project.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("within(com.kreuzfeuer.mirea.trpp.final_project.service.impl.*)")
    public void servicePointcut() {
    }

    @Pointcut("within(com.kreuzfeuer.mirea.trpp.final_project.controller.*)")
    public void controllerPointcut() {
    }

    @Before(value = "servicePointcut() || controllerPointcut()")
    public void allServiceAndControllerMethodAdvice(JoinPoint joinPoint)  {
        log.info("Start Method - {}, with args - {}", joinPoint.toString(), joinPoint.getArgs());
    }


}
