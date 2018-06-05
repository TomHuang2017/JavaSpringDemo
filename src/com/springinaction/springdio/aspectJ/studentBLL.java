package com.springinaction.springdio.aspectJ;

import com.springinaction.springdio.aspectJ.insertRecordIntoDB;

//正常业务-一个真实例子中的Student，插入表结构的例子，实现插入数据库的那个接口
public class studentBLL implements insertRecordIntoDB {
    @Override
    public void recordInsert(int firstNumber,String secondCode) {
        System.out.println("Inserting a new record into student table:"+
        "firstNumber="+firstNumber+
        "secondCode="+secondCode);
    }

    public void getRecord()
    {
        System.out.println("Geting record from db");
    }
}
