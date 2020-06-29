package com.company.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
public class UserRepoAspectLogging {

    @Before("execution(* com.company.dao.impl.UserRepository.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.err.println("\nBEFORE BEGIN ---");
        System.out.println("UserRepository." + joinPoint.getSignature().getName() + "()");
        System.err.println("BEFORE END ---\n");
    }

    @Around("execution(* com.company.dao.impl.UserRepository.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("\nAROUND BEGIN ---");
        System.out.println("UserRepository." + joinPoint.getSignature().getName() + "()");
        System.out.println("ARGUMENTS - " + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();
        System.err.println("AROUND END ---\n");
        return result;
    }

    @After("execution(* com.company.dao.impl.UserRepository.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.err.println("\nAFTER BEGIN ---");
        System.out.println("UserRepository." + joinPoint.getSignature().getName() + "()");
        System.err.println("AFTER END ---\n");
    }

    @AfterReturning(
            pointcut = "execution(* com.company.dao.impl.UserRepository.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.err.println("\nAFTER_RETURNING BEGIN ---");
        System.out.println("UserRepository." + joinPoint.getSignature().getName() + "()");
        System.out.println("UserRepository." + joinPoint.getSignature().getName() + "()" + " returned value is: " + result);
        System.err.println("AFTER_RETURNING END ---\n");
    }

    @AfterThrowing(
            pointcut = "execution(* com.company.dao.impl.UserRepository.*(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.err.println("\nAFTER_THROWING BEGIN ---");
        System.out.println("UserRepository." + joinPoint.getSignature().getName() + "()");
        System.out.println("THROW: " + error);
        System.err.println("AFTER_THROWING END ---\n");
    }
}
