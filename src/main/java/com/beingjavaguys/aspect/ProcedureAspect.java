package com.beingjavaguys.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Aspect
public class ProcedureAspect {

    static Logger logger = LoggerFactory.getLogger(ProcedureAspect.class);

    @Pointcut("execution(* com.beingjavaguys.aspect.*.*(..)) ")
    public void targetMethods() {
    }

    @Before("@annotation(com.beingjavaguys.aspect.Procedure)")
    public void preHandle() {
        logger.info("Aspect :: preHandle");
    }

    @AfterReturning(
            pointcut = "@annotation(com.beingjavaguys.aspect.Procedure)",
            returning = "retVal")
    public void postHandle(Object retVal) {
        logger.info("Aspect :: postHandle, retVal={}", retVal != null ? retVal.toString() : "null");
    }

    @Around("@annotation(com.beingjavaguys.aspect.Procedure)")
    public Object handle(ProceedingJoinPoint pjp) {

        logger.info("Aspect :: around - start");

        Object[] args;
        try {
            args = pjp.getArgs();
            return args == null ? pjp.proceed() : pjp.proceed(args);
        } catch (Throwable e) {
            logger.info("Aspect :: handleException");
            int statusCode = 500;
            String statusMessage = "unknown";
            if (e instanceof ProcedureException) {
                statusCode = ((ProcedureException) e).getStatusCode();
                statusMessage = ((ProcedureException) e).getStatusMessage();
            } else if (e instanceof IllegalArgumentException) {
                statusCode = 400;
                statusMessage = "Invalid parameter";
            }
            Output<Object> error = new Output<Object>(UUID.randomUUID().toString(), statusCode, statusMessage);
            return error;
        } finally {
            logger.info("Aspect :: around - end");
        }
    }
}
