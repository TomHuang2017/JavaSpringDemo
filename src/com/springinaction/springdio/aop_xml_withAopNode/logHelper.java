package com.springinaction.springdio.aop_xml_withAopNode;

public class logHelper {
    public logHelper() {}

    public void InsertPointCut(int firstNumber,String secondCode) {

        System.out.println("This is a point function()");
    }

    public void preInsertRecordPointXXX(int firstNumber,String secondCode) {
        System.out.println("aop_xml_withAopNode->pre insert and do something:"+"firstNumber="+firstNumber+
                " secondCode="+secondCode);
    }

    public void postInsertRecordPointXXX(int firstNumber,String secondCode) {
        System.out.println("aop_xml_withAopNode->post insert and do something:"+"firstNumber="+firstNumber+
                " secondCode="+secondCode);
    }

    public void succeedInsertRecordPointXXX(int firstNumber,String secondCode){
        System.out.println("aop_xml_withAopNode->succeed to insert record!:"+"firstNumber="+firstNumber+
                " secondCode="+secondCode);}

    public void failedToInsertRecordPointXXX(int firstNumber,String secondCode){
        System.out.println("aop_xml_withAopNode->failed to insert record!:"+"firstNumber="+firstNumber+
            " secondCode="+secondCode);}
}
