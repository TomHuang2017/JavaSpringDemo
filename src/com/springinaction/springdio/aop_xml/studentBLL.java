package com.springinaction.springdio.aop_xml;

public class studentBLL implements insertRecordIntoDB {
    @Override
    public void insertRecord() {
        System.out.println("Inserting a new record into student table");
    }

    public void getRecord()
    {
        System.out.println("Geting record from db");
    }
}
