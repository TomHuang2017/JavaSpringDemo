package com.springinaction.springdio.aop_xml;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class logHelper implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) {
        System.out.println("preInsert a new record into DB");
    }

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) {
        System.out.println("postInsert a new record into DB");
    }
}
