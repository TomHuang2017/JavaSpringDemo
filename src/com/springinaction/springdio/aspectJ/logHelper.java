package com.springinaction.springdio.aspectJ;

import org.aspectj.lang.annotation.*;

@Aspect
public class logHelper {
    public logHelper() {}
    //返回任意类型，在任意命名空间下，任意类中的参数为任意的 recordInsert方法，
    @Pointcut("execution(* *.recordInsert(int,String)) &&args(firstNumber,secondCode)")
    public void InsertPointCut(int firstNumber,String secondCode) {
        System.out.println("This is a point function()");
    }
    @Before("InsertPointCut(firstNumber,secondCode)")
    public void preInsertRecordPointXXX(int firstNumber,String secondCode) {
        System.out.println("pre insert and do something:"+"firstNumber="+firstNumber+
                "secondCode="+secondCode);
    }
    @After("InsertPointCut(firstNumber,secondCode)")
    public void postInsertRecordPointXXX(int firstNumber,String secondCode) {
        System.out.println("post insert and do something:"+"firstNumber="+firstNumber+
                "secondCode="+secondCode);
    }
    //执行成功后通知
    @AfterReturning("InsertPointCut(firstNumber,secondCode)")
    public void succeedInsertRecordPointXXX(int firstNumber,String secondCode){System.out.println("succeed to insert record!:"+"firstNumber="+firstNumber+
            "secondCode="+secondCode);}
    //执行错误后通知
    @AfterThrowing("InsertPointCut(firstNumber,secondCode)")
    public void failedToInsertRecordPointXXX(int firstNumber,String secondCode){System.out.println("failed to insert record!:"+"firstNumber="+firstNumber+
            "secondCode="+secondCode);}
}
