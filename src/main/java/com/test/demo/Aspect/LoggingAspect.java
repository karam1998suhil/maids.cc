package com.test.demo.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.test.demo.controller.*.*(..))")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logMethodStart(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Calling method: {}", methodName);
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} returned with result: {}", methodName, result);
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception in method {}: {}", methodName, exception.getMessage());
    }

    @Around("execution(* com.test.demo.controller.BookController.addBook(..)) || " +
            "execution(* com.test.demo.controller.BookController.updateBookById(..)) || " +
            "execution(* com.test.demo.controller.BorrowingRecordController.borrowBook(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Execution time of {} is {} ms", joinPoint.getSignature().getName(), (endTime - startTime));
        return proceed;
    }
}

