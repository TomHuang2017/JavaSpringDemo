package com.springinaction.springdio.aspectJ;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class logHelper {
    public logHelper() {
    }

    @Pointcut("execution(* *.Insert(..))")
    public void insertRecordPointXX() {
        System.out.println("This is a point function()");
    }

    @Before("insertRecordPointXX()")
    public void preInsertRecordPointXXX() {
        System.out.println("pre insert and do something");
    }

    @After("insertRecordPointXX()")
    public void postInsertRecordPointXXX() {
        System.out.println("post insert and do something");
    }

}
