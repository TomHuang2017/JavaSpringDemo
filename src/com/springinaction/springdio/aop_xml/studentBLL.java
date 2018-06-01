package com.springinaction.springdio.aop_xml;

//正常业务-一个真实例子中的Student，插入表结构的例子，实现插入数据库的那个接口
public class studentBLL implements insertRecordIntoDB {
    @Override
    public void recordInsert() {
        System.out.println("Inserting a new record into student table");
    }

    public void getRecord()
    {
        System.out.println("Geting record from db");
    }
}
