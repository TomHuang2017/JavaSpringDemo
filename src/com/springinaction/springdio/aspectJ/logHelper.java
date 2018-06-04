package com.springinaction.springdio.aspectJ;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class logHelper {
    public logHelper() {
    }

    @Pointcut("execution(* *.recordInsert(..))")
    public void InsertPointCut() {
        System.out.println("This is a point function()");
    }

    @Before("InsertPointCut()")
    public void preInsertRecordPointXXX() {
        System.out.println("pre insert and do something");
    }

    @After("InsertPointCut()")
    public void postInsertRecordPointXXX() {
        System.out.println("post insert and do something");
    }

}
